# Sum up
- XML: Xây dựng giao diện người dùng.
- Activity: Hiển thị giao diện người dùng và tương tác với người dùng.
- Intent: Kết nối các thành phần của Android trong quá trình chạy. Có thể được dùng để chuyển dữ liệu giữa các Activity hoặc khởi chạy Service

- Giao diện người dùng (UI):  
+ Thiết kế layout bằng XML và Java/Kotlin: Layout XML được sử dụng để xây dựng giao diện người dùng, trong khi Java hoặc Kotlin được sử dụng để tương tác và điều khiển các thành phần trong layout.  
+ Sử dụng các widget và control cơ bản: Android cung cấp một loạt các widget và control như TextView, Button, EditText, ListView, RecyclerView, và nhiều hơn nữa để xây dựng giao diện người dùng.  
+ Xử lý sự kiện người dùng: Sự kiện người dùng như nhấn nút, chạm vào màn hình, hoặc nhập liệu được xử lý thông qua các phương thức callback như onClick(), onTouchListener(), và onKeyListener().

### Thuộc tính kích thước
| Thuộc tính         | Nghĩa                                                                  | Ví dụ                                                                                       |
| ------------------ | ---------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------- |
| `match_parent`     | **Chiếm hết không gian** của phần cha (container) theo hướng đó        | Nếu cha rộng 300dp, con cũng rộng 300dp                                                               |
| `wrap_content`     | **Vừa đủ nội dung bên trong**                                          | Nếu TextView chỉ có chữ “A”, thì nó nhỏ vừa chữ A                                                     |
| `0dp` *(đặc biệt)* | Dùng trong **ConstraintLayout** để “kéo giãn linh hoạt” theo ràng buộc | Android sẽ tính tự động chiều rộng/cao dựa vào constraint (thường kết hợp với `layout_constraint...`) |

### ConstraintLayout
| Tên thuộc tính                       | Nghĩa              | 
| ------------------------------------ | ------------------ | 
| `layout_constraintTop_toTopOf`       | Dính trên với trên |
| `layout_constraintTop_toBottomOf`    | Dính trên với dưới |
| `layout_constraintBottom_toTopOf`    | Dính dưới với trên |
| `layout_constraintBottom_toBottomOf` | Dính dưới với dưới |
| `layout_constraintStart_toStartOf`   | Dính trái với trái |
| `layout_constraintEnd_toEndOf`       | Dính phải với phải |

-> Of"@id/..."   
-> @id/parent hoặc parent là **layout cha**
