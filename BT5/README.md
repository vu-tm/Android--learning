# 📱 Android Intent & Intent Filter

## Giới thiệu

- **Intents** là một objects của android.content.Intent. Intents sẽ được gửi đến hệ thống android để xác định hành động bạn muốn thực hiện, đối tượng bạn muốn xử lý.
<img width="589" height="227" alt="image" src="https://github.com/user-attachments/assets/4478fb39-3f1d-4c5f-9dbc-15bc493d4cae" />
  
- **Intent Filter** cho phép một component (như Activity, Service, BroadcastReceiver) *khai báo* rằng nó có thể xử lý những loại Intent nào.
Intent Filter là phần khai báo trong AndroidManifest.xml cho biết thành phần nào có thể nhận và xử lý các loại Intent nào (action, data, category).
Nếu một component **không có Intent Filter**, nó chỉ có thể được gọi bằng **Explicit Intent.**

> **Intent** = “Tôi muốn làm gì đó.”  
> **Intent Filter** = “Tôi có thể xử lý việc này nếu phù hợp.”
<img width="1112" height="246" alt="image" src="https://github.com/user-attachments/assets/b15a8aad-4c94-4807-8481-8dfc64475036" />

---

## Chức năng của Intent  

| Mục đích                                | Giải thích                                                                                 | Ví dụ                                                            |
| --------------------------------------- | ------------------------------------------------------------------------------------------ | ---------------------------------------------------------------- |
| 1️⃣ **Chuyển đổi giữa các Activity**    | Dùng để **mở màn hình mới** hoặc **truyền dữ liệu** giữa các Activity trong cùng ứng dụng. | Mở `DetailActivity` từ `MainActivity`                            |
| 2️⃣ **Gửi dữ liệu giữa các thành phần** | Truyền thông tin qua `putExtra()` và nhận bằng `getIntent().getStringExtra()`              | Gửi tên người dùng, ID sản phẩm, v.v.                            |
| 3️⃣ **Tương tác với ứng dụng khác**     | Gửi yêu cầu cho **ứng dụng hệ thống hoặc bên thứ ba** xử lý.                               | Gọi điện, gửi email, mở bản đồ, chụp ảnh, chia sẻ nội dung, v.v. |

## Cấu trúc Intent cơ bản

| Thành phần      | Ý nghĩa                                                                                                                                                                                                                            | Ví dụ                                                                              |
| --------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------- |
| **`action`**    | Xác định **hành động** mà Intent sẽ thực hiện. Có thể là hành động do Android định nghĩa sẵn (như `ACTION_VIEW`, `ACTION_SEND`, `ACTION_DIAL`) hoặc do **lập trình viên tự định nghĩa** (thường dùng trong **BroadcastReceiver**). | `intent.setAction(Intent.ACTION_VIEW);`                                            |
| **`data`**      | Là **dữ liệu mà hành động sẽ xử lý**, thường ở dạng **URI** (Uniform Resource Identifier). Dữ liệu này có thể là một liên kết web, số điện thoại, file, hình ảnh, v.v.                                                             | `intent.setData(Uri.parse("https://example.com"));`                                |
| **`type`**      | Chỉ định **định dạng MIME type** của dữ liệu (ví dụ `text/plain`, `image/jpeg`). Hệ thống Android dùng thông tin này để chọn ứng dụng phù hợp để xử lý Intent.                                                                     | `intent.setType("text/plain");`                                                    |
| **`category`**  | Xác định **ngữ cảnh hoặc nhóm** của Intent. Hệ thống dùng category để biết Intent này được phép khởi chạy trong điều kiện nào.                                                                                                     | `Intent.CATEGORY_DEFAULT`, `Intent.CATEGORY_BROWSABLE`, `Intent.CATEGORY_LAUNCHER` |
| **`extras`**    | Chứa **dữ liệu bổ sung (key–value pairs)** được truyền kèm theo Intent, dùng để gửi thông tin sang Activity/Service đích. Dữ liệu được lưu trong một **Bundle**.                                                                   | `intent.putExtra("id", 1);` hoặc `intent.putExtras(bundle);`                       |
| **`component`** | Xác định **thành phần cụ thể (ComponentName)** mà Intent muốn gọi đến, ví dụ: Activity, Service, BroadcastReceiver. Dùng cho **Explicit Intent**.                                                                                  | `new Intent(this, DetailActivity.class);`                                          |
| **`flags`**     | Là **tùy chọn điều khiển cách hệ thống Android khởi chạy Activity** (ví dụ tạo Activity mới, xóa Activity cũ, chạy trong task mới,...).                                                                                            | `intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);`                                  |


---

## Phân loại Intent

| Loại | Mô tả | Ví dụ |
|------|--------|--------|
| **Explicit Intent** (Tường minh) | Chỉ rõ component đích (Activity, Service cụ thể) | Mở `DetailActivity` trong app |
| **Implicit Intent** (Không tường minh) | Chỉ nêu hành động (`ACTION_...`) và dữ liệu (`Uri`), để hệ thống tìm component phù hợp qua `intent-filter` | Mở trình duyệt, gọi điện, chụp ảnh, gửi email |

---


# Sử dụng
## Explicit Intent
Để khởi chạy một Activity cụ thể, hãy sử dụng Intent tường minh

Tạo một Intent
```java
Intent intent = new Intent(this, ActivityName.class);
```
Sử dụng Intent để khởi động Activity
```java
startActivity(intent);
```

## Implicit intent
Để yêu cầu Android tìm một Activity có thể xử lý yêu cầu của bạn, hãy sử dụng Intent ngầm định

Tạo một Intent
```java
Intent intent = new Intent(action, url);
```
Sử dụng Intent để khởi động Activity
```java
startActivity(intent);
```
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


