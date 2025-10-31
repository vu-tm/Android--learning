# Sum up
- XML: Xây dựng giao diện người dùng.
- Activity: Hiển thị giao diện người dùng và tương tác với người dùng.
- Intent: Kết nối các thành phần của Android trong quá trình chạy. Có thể được dùng để chuyển dữ liệu giữa các Activity hoặc khởi chạy Service 

- Giao diện người dùng (UI):  
+ Thiết kế layout bằng XML và Java/Kotlin: Layout XML được sử dụng để xây dựng giao diện người dùng, trong khi Java hoặc Kotlin được sử dụng để tương tác và điều khiển các thành phần trong layout.  
+ Sử dụng các widget và control cơ bản: Android cung cấp một loạt các widget và control như TextView, Button, EditText, ListView, RecyclerView, và nhiều hơn nữa để xây dựng giao diện người dùng.  
+ Xử lý sự kiện người dùng: Sự kiện người dùng như nhấn nút, chạm vào màn hình, hoặc nhập liệu được xử lý thông qua các phương thức callback như onClick(), onTouchListener(), và onKeyListener().

- LinerLayout: Cho phép sắp xếp các view trên giao diện theo chiều ngang hoặc dọc.

| STT | Thuộc tính       | Nội dung                                      |
|-----|------------------|-----------------------------------------------|
| 1   | orientation      | Vertical: theo chiều dọc<br>Horizontal: theo chiều ngang |
| 2   | background       | Set màu nền                                   |
| 3   | id               | Giống tên biến, sử dụng khi code              |
| 4   | layout_width     | Chiều rộng layout    |
| 5   | layout_height    | Chiều cao layout, chia như kiểu grid     |
| 6   | gravity          | Căn chỉnh nội dung bên trong: center, left, right, top, bottom |
| 7   | layout_gravity   | Căn chỉnh view trong parent: center, left, right, top, bottom |
| 8   | margin           | Khoảng cách bên ngoài: marginStart, marginEnd, marginTop, marginBottom |
| 9   | padding          | Khoảng cách bên trong: paddingStart, paddingEnd, paddingTop, paddingBottom |
| 10  | weight           | Chia không gian theo tỷ lệ (trong LinearLayout) |
| 11  | text             | Nội dung văn bản hiển thị                     |
| 12  | textSize         | Kích thước chữ: sp, dp                        |
| 13  | textColor        | Màu chữ                                       |
| 14  | src              | Nguồn hình ảnh (cho ImageView)                |
| 15  | visibility       | Hiển thị: visible, invisible, gone            |

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

### xmlns
| Thành phần            | Ý nghĩa                                                      |
| --------------------- | ------------------------------------------------------------ |
| `xmlns`               | XML namespace – khai báo “nguồn gốc” của thuộc tính          |
| `xmlns:android="..."` | Khai báo các thuộc tính chuẩn của Android                    |
| `xmlns:app="..."`     | Khai báo thuộc tính tùy chỉnh (thường dùng trong thư viện)   |
| Nếu thiếu `xmlns`     | File XML sẽ lỗi vì không biết thuộc tính thuộc namespace nào |


### inputType
| Giá trị             | Chức năng                                         |
| ------------------- | ------------------------------------------------- |
| `text`              | Văn bản bình thường (không tự viết hoa)           |
| `textCapSentences`  | Viết hoa chữ cái đầu **mỗi câu**                  |
| `textCapCharacters` | Viết hoa **tất cả các ký tự**                     |
| `textEmailAddress`  | Dành cho nhập email, hiển thị bàn phím có ký tự @ |
| `textPassword`      | Ẩn ký tự nhập (••••••)                            |
| `number`            | Chỉ cho nhập số                                   |

