# SUM UP ACTIVITY

### Vòng đời cơ bản:
| Hàm           | Ý nghĩa                                      |
| ------------- | -------------------------------------------- |
| `onCreate()`  | Khởi tạo activity, set layout, khởi tạo biến |
| `onStart()`   | Activity sắp hiển thị                        |
| `onResume()`  | Activity bắt đầu tương tác với người dùng    |
| `onPause()`   | Tạm dừng (khi có activity khác đè lên)       |
| `onStop()`    | Không còn hiển thị                           |
| `onDestroy()` | Bị hủy hoàn toàn                             |

### Kết nối Activity với Layout
<img width="1024" height="391" alt="image" src="https://github.com/user-attachments/assets/21b54bc5-6e38-4b66-8b8b-b9f59f36fdba" />


### TOAST
Toast là một thông báo ngắn hiển thị tạm thời trên màn hình (thường ở phía dưới) để báo cho người dùng một sự kiện nhỏ, không làm gián đoạn hoạt động hiện tại.
##### Cú pháp:
```java
Toast.makeText(context, text, duration).show();
```
- context: ngữ cảnh (thường là this hoặc getApplicationContext())
- text: nội dung hiển thị
- duration: thời gian hiển thị (Toast.LENGTH_SHORT hoặc Toast.LENGTH_LONG)

| **Mục**                                       | **Giải thích**                                                                                                                                                                                         | **Ví dụ minh họa / Cú pháp**                                                                                                                                     | **Ghi chú quan trọng**                                                              |
| ---------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- |
| Các giá trị thời gian**                  | `Toast.LENGTH_SHORT` (~2 giây)  <br> `Toast.LENGTH_LONG` (~3.5 giây)                                                                                                                                   | `java Toast.makeText(this, "Đã lưu!", Toast.LENGTH_LONG).show(); `                                                                                               | Không thể tùy chỉnh chính xác số giây (trừ khi dùng `Handler` hoặc `Custom Toast`). |
| Hiển thị trong Activity**                | Dùng `this` làm context                                                                                                                                                                                | `java Toast.makeText(this, "Xin chào", Toast.LENGTH_SHORT).show(); `                                                                                             | Được dùng phổ biến nhất.                                                            |
| Hiển thị trong Fragment**                | Dùng `getActivity()` hoặc `requireContext()`                                                                                                                                                           | `java Toast.makeText(requireContext(), "Từ Fragment", Toast.LENGTH_SHORT).show(); `                                                                              | Tránh dùng `getContext()` nếu có thể `null`.                                        |
| Hiển thị trong Adapter hoặc class khác** | Dùng `context` truyền từ ngoài vào                                                                                                                                                                     | `java Toast.makeText(context, "Trong Adapter", Toast.LENGTH_SHORT).show(); `                                                                                     | Luôn đảm bảo context hợp lệ.                                                        |
| Tùy chỉnh vị trí hiển thị (setGravity)** | Dùng `setGravity()` để di chuyển Toast lên giữa, trên, dưới,...                                                                                                                                        | `java Toast toast = Toast.makeText(this, "Giữa màn hình", Toast.LENGTH_SHORT); toast.setGravity(Gravity.CENTER, 0, 0); toast.show(); `                           | Dùng các hằng số: `Gravity.TOP`, `CENTER`, `BOTTOM`, ...                            |
| Custom layout (Toast tự thiết kế)**      | Dùng file XML làm giao diện tùy chỉnh cho Toast                                                                                                                                                        | `java View v = getLayoutInflater().inflate(R.layout.custom_toast, null); Toast t = new Toast(this); t.setView(v); t.setDuration(Toast.LENGTH_SHORT); t.show(); ` | Dùng khi cần thông báo đẹp hơn (icon, màu, kiểu chữ…).                              |

### ems
ems là đơn vị đo độ rộng của văn bản trong TextView hoặc EditText, được tính theo chiều rộng của ký tự chữ "M" trong font hiện tại.  
ems="5" → đủ chỗ cho 5 ký tự

### MotionEvent
| Hằng số (`MotionEvent`)     | Khi nào xảy ra                                                                                                    | Mô tả / Ứng dụng thực tế                                                |
| --------------------------- | ----------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------- |
| **`ACTION_DOWN`**           | Khi **ngón tay đầu tiên chạm xuống** màn hình                                                                     | Sự kiện bắt đầu — thường dùng để nhận diện “bắt đầu chạm” hoặc nhấn nút |
| **`ACTION_UP`**             | Khi **ngón tay nhấc khỏi** màn hình                                                                               | Kết thúc chạm — thường dùng để xác nhận click hoặc thả nút              |
| **`ACTION_MOVE`**           | Khi **ngón tay di chuyển** trên màn hình                                                                          | Dùng để kéo, vẽ, rê đối tượng, di chuyển hình,…                         |
| **`ACTION_CANCEL`**         | Khi hệ thống **hủy bỏ** sự kiện chạm (ví dụ bị gián đoạn bởi cuộc gọi, thông báo, hay ngón tay ra khỏi vùng View) | Dùng để reset trạng thái tạm, hủy thao tác                              |
| **`ACTION_OUTSIDE`**        | Khi sự kiện chạm xảy ra **ngoài vùng View** hiện tại                                                              | Ít dùng, có thể dùng khi cần phát hiện chạm ngoài popup/dialog          |
| **`ACTION_POINTER_DOWN`**   | Khi **ngón tay thứ 2 (hoặc thứ N)** chạm xuống                                                                    | Dùng cho thao tác đa điểm (multi-touch) như zoom, xoay,…                |
| **`ACTION_POINTER_UP`**     | Khi **một ngón tay (không phải đầu tiên)** nhấc lên                                                               | Cũng thuộc nhóm multi-touch                                             |
| **`ACTION_HOVER_MOVE`**     | Khi **con trỏ/chuột/pen di chuyển** mà **chưa chạm**                                                              | Dùng cho stylus hoặc chuột, không phải cảm ứng thông thường             |
| **`ACTION_SCROLL`**         | Khi có **cuộn** (scroll) do chuột hoặc bánh xe                                                                    | Ít dùng trong cảm ứng                                                   |
| **`ACTION_BUTTON_PRESS`**   | Khi **nhấn nút chuột / bút stylus**                                                                               | Dùng trong thiết bị có chuột hoặc pen                                   |
| **`ACTION_BUTTON_RELEASE`** | Khi **thả nút chuột / bút stylus**                                                                                | Tương tự, ít dùng với điện thoại cảm ứng                                |
