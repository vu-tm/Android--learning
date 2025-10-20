# 📱 Android Intent & Intent Filter

## Giới thiệu

Trong Android, **Intents** là một objects của android.content.Intent. Intents sẽ được gửi đến hệ thống android để xác định hành động bạn muốn thực hiện, đối tượng bạn muốn xử lý.
**Intent Filter** cho phép một component (như Activity, Service, BroadcastReceiver) *khai báo* rằng nó có thể xử lý những loại Intent nào.

> **Intent** = “Tôi muốn làm gì đó.”  
> **Intent Filter** = “Tôi có thể xử lý việc này nếu phù hợp.”

---

## Chức năng của Intent  

| Mục đích                                | Giải thích                                                                                 | Ví dụ                                                            |
| --------------------------------------- | ------------------------------------------------------------------------------------------ | ---------------------------------------------------------------- |
| 1️⃣ **Chuyển đổi giữa các Activity**    | Dùng để **mở màn hình mới** hoặc **truyền dữ liệu** giữa các Activity trong cùng ứng dụng. | Mở `DetailActivity` từ `MainActivity`                            |
| 2️⃣ **Gửi dữ liệu giữa các thành phần** | Truyền thông tin qua `putExtra()` và nhận bằng `getIntent().getStringExtra()`              | Gửi tên người dùng, ID sản phẩm, v.v.                            |
| 3️⃣ **Tương tác với ứng dụng khác**     | Gửi yêu cầu cho **ứng dụng hệ thống hoặc bên thứ ba** xử lý.                               | Gọi điện, gửi email, mở bản đồ, chụp ảnh, chia sẻ nội dung, v.v. |


## Phân loại Intent

| Loại | Mô tả | Ví dụ |
|------|--------|--------|
| **Explicit Intent** (Tường minh) | Chỉ rõ component đích (Activity, Service cụ thể) | Mở `DetailActivity` trong app |
| **Implicit Intent** (Ẩn danh) | Chỉ nêu hành động (`ACTION_...`) và dữ liệu (`Uri`), để hệ thống tìm component phù hợp qua `intent-filter` | Mở trình duyệt, gọi điện, chụp ảnh, gửi email |

---

## Cấu trúc Intent cơ bản

| Thành phần | Ý nghĩa | Ví dụ |
|-------------|----------|--------|
| `action` | Hành động cần thực hiện | `Intent.ACTION_VIEW`, `Intent.ACTION_SEND` |
| `data` | Dữ liệu (URI, MIME type) | `Uri.parse("https://example.com")` |
| `category` | Bổ sung ngữ cảnh | `Intent.CATEGORY_DEFAULT`, `Intent.CATEGORY_BROWSABLE` |
| `extras` | Dữ liệu bổ sung (key-value) | `intent.putExtra("id", 1)` |
| `component` | Chỉ rõ Activity/Service đích | `new Intent(this, DetailActivity.class)` |

---

## Explicit Intent — ví dụ cơ bản

**MainActivity.java**
```java
Intent intent = new Intent(MainActivity.this, DetailActivity.class);
intent.putExtra("username", "Tru");
intent.putExtra("age", 20);
startActivity(intent);
```

**DetailActivity.java**
```java
String name = getIntent().getStringExtra("username");
int age = getIntent().getIntExtra("age", 0);
```

👉 Explicit Intent **không cần `intent-filter`** trong `Manifest`.

---

## Implicit Intent — ví dụ mở website

**Manifest**
```xml
<activity android:name=".WebHandlerActivity">
    <intent-filter>
        <action android:name="android.intent.action.VIEW" />
        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.BROWSABLE" />
        <data android:scheme="https" android:host="www.example.com" />
    </intent-filter>
</activity>
```

**Gọi từ code**
```java
Intent intent = new Intent(Intent.ACTION_VIEW);
intent.setData(Uri.parse("https://www.example.com"));
startActivity(intent);
```

---

## Implicit Intent — gọi điện thoại

```java
Intent intent = new Intent(Intent.ACTION_DIAL);
intent.setData(Uri.parse("tel:0123456789"));
startActivity(intent);
```

---

## Intent Filter — cấu trúc chi tiết

```xml
<intent-filter>
    <action android:name="android.intent.action.VIEW" />
    <category android:name="android.intent.category.DEFAULT" />
    <data
        android:scheme="https"
        android:host="www.example.com"
        android:pathPrefix="/products" />
</intent-filter>
```

| Thành phần | Mô tả |
|-------------|--------|
| `<action>` | Hành động mà Activity có thể xử lý |
| `<category>` | Bổ sung loại hành động (ví dụ: `DEFAULT`, `BROWSABLE`) |
| `<data>` | Mô tả kiểu dữ liệu hoặc URI mà Activity xử lý được |

---

## Quy tắc so khớp Intent & Intent Filter

Một `Intent` khớp với một `Intent Filter` khi:

| Điều kiện | Mô tả |
|------------|--------|
| ✅ **Action match** | Action của Intent trùng với 1 action trong filter |
| ✅ **Category match** | Tất cả category của Intent có trong filter |
| ✅ **Data match** | Scheme, host, path hoặc MIME type trùng khớp |

> Nếu có nhiều component phù hợp, hệ thống hiển thị “chooser” để người dùng chọn app.

---

## Nhận dữ liệu trả về từ Activity khác

Từ Android 11+, dùng **Activity Result API** (thay `startActivityForResult`).

**MainActivity.java**
```java
ActivityResultLauncher<Intent> launcher =
    registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        result -> {
            if (result.getResultCode() == Activity.RESULT_OK) {
                String msg = result.getData().getStringExtra("result");
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            }
        }
    );

Intent intent = new Intent(this, SecondActivity.class);
launcher.launch(intent);
```

**SecondActivity.java**
```java
Intent resultIntent = new Intent();
resultIntent.putExtra("result", "Hello MainActivity!");
setResult(RESULT_OK, resultIntent);
finish();
```

---

## Thuộc tính liên quan trong Manifest

| Thuộc tính | Mô tả |
|-------------|--------|
| `android:exported` | Cho phép (hoặc cấm) app khác gọi component này. Bắt buộc từ Android 12 nếu có `intent-filter`. |
| `android:enabled` | Bật/tắt component. |
| `android:permission` | Giới hạn quyền truy cập component. |

---

## Các Intent phổ biến của hệ thống

| Hành động | Mô tả | Ví dụ |
|------------|--------|--------|
| `ACTION_VIEW` | Xem nội dung | Mở trang web, bản đồ |
| `ACTION_DIAL` | Mở trình quay số | `Uri.parse("tel:...")` |
| `ACTION_SEND` | Gửi dữ liệu (share) | Chia sẻ ảnh, text |
| `ACTION_SENDTO` | Gửi tin nhắn/email | `mailto:` hoặc `smsto:` |
| `ACTION_PICK` | Chọn dữ liệu từ danh bạ/thư viện | |
| `ACTION_IMAGE_CAPTURE` | Chụp ảnh | Mở camera |

---

## Deep Links & App Links

| Loại | Mô tả | Ghi chú |
|------|--------|--------|
| **Deep Link** | Cho phép mở trực tiếp Activity khi nhấn vào một URL cụ thể. | Cấu hình bằng `intent-filter` có `scheme`, `host`. |
| **App Link** | Deep Link có xác minh domain (từ Android 6+). | Cần file `assetlinks.json` trên server để xác thực. |

---

## Checklist khi tạo Intent Filter

✅ Có `action` đúng.  
✅ Có `CATEGORY_DEFAULT` nếu dùng `startActivity()`.  
✅ `data` (scheme/host/path/mime) đúng hoặc đủ tổng quát.  
✅ Khai `android:exported` rõ ràng.  
✅ Test bằng `adb`:

```bash
adb shell am start -a android.intent.action.VIEW -d "https://www.example.com/item/123"
```

---

## Lỗi thường gặp

| Lỗi | Nguyên nhân |
|------|---------------|
| Activity không hiển thị trong chooser | Thiếu `CATEGORY_DEFAULT` |
| Không khớp Intent Filter | `scheme` hoặc `host` không đúng |
| Không mở được link | `android:exported="false"` hoặc `data` sai |
| Crash khi nhận dữ liệu | Quên `Uri` hoặc chưa cấp quyền `FLAG_GRANT_READ_URI_PERMISSION` |

---

## Tóm tắt nhanh

| Mục | Explicit Intent | Implicit Intent |
|------|------------------|------------------|
| Chỉ rõ component | ✅ | ❌ |
| Cần Intent Filter | ❌ | ✅ |
| Dùng cho | Gọi Activity trong app | Mở hành động hệ thống hoặc app khác |
| Ví dụ | `new Intent(this, DetailActivity.class)` | `new Intent(Intent.ACTION_VIEW, Uri.parse("https://..."))` |

---
