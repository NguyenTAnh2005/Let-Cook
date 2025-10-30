package com.example.let__cook;

import static android.content.Context.MODE_PRIVATE;
import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class HelperFunction {
    DatabaseSQLite databaseSQLite;
    // 2 Hàm chuyển activity
    public static void ConvertActivityNoReturn(View id, Context act_current, Class<?> act_next){
        id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent convertView=new Intent(act_current,act_next);
                act_current.startActivity(convertView);
                ((Activity)act_current).finish();
            }
        });
    }
    public static void ConvertActivityHaveReturn(View id, Context act_current, Class<?> act_next){
        id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent convertView=new Intent(act_current,act_next);
                act_current.startActivity(convertView);
            }
        });
    }

    // Ham toats
    public static void ToastMessage(Context context, String mess){
        Toast.makeText(context, mess, Toast.LENGTH_SHORT).show();
    }

    /*Thêm dữ liệu cho Account*/
    public static void InsertData_Account(Context context) {
        DatabaseSQLite databaseSQLite = new DatabaseSQLite(context, "APP_TEACHING_COOK.sqlite", null, 1);

        String INSERT_ACCOUNT = "INSERT OR IGNORE INTO Account VALUES " +
                "('user_0', 'Nguyễn Tuấn', 'Anh', '2005-10-06', '23050118@student.bdu.edu.vn', 'Anh123@', 'admin', 0)," +
                "('user_00', 'Lê Ngọc', 'Sang', '2005-09-16', '23050084@student.bdu.edu.vn', 'Sang123@', 'admin', 0)," +
                "('user_01', 'US', 'Quest', '2000-01-01', 'tuananhnguyen2555@gmail.com', '123456789A@', 'user', 1)," +
                "('user_02', 'UR', 'Quest', '2001-09-09', '91lengocsang1920@gmail.com', '1029384756@', 'user', 1)," +
                "('user_03', 'Nguyễn Văn', 'Tiến', '2005-11-07', '1234567A@gmail.com', '123456789A', 'user', 0);";

        databaseSQLite.QuerySQL(INSERT_ACCOUNT);
    }

    // Ham them dl cho bang food
    public static void InsertData_Food(Context context) {
        DatabaseSQLite databaseSQLite = new DatabaseSQLite(context, "APP_TEACHING_COOK.sqlite", null, 1);
        String INSERT_STIR_FRY_FOOD = "INSERT OR IGNORE INTO Food VALUES" +
                "('stir_fry_1', 'Rau muống xào tỏi', 2.5, 'stir_fry_1.png', " +
                "'Rau muống xào tỏi là một món ăn dân dã, quen thuộc trong ẩm thực Việt Nam', " +
                "'https://www.youtube.com/watch?v=vu84WHUUALU', " +
                "'Khi xào rau muống, nên ngâm rau luộc vào nước đá để giữ màu xanh và độ giòn','stir_fry')," +

                "('stir_fry_2', 'Hến xào giá đỗ', 2.5, 'stir_fry_2.png', " +
                "'Rau muống xào tỏi là một món ăn dân dã, quen thuộc trong ẩm thực Việt Nam', " +
                "'https://www.youtube.com/watch?v=vu84WHUUALU', " +
                "'Khi xào rau muống, nên ngâm rau luộc vào nước đá để giữ màu xanh và độ giòn','stir_fry')," +

                "('stir_fry_3', 'Cà tím xào ếch', 3.5, 'stir_fry_3.png', " +
                "'Ếch xào cà tím là món ăn đậm đà, thơm ngon, hấp dẫn trong những ngày trời mưa lạnh. Thịt ếch là thực phẩm có vị ngọt, béo ngậy và nó rất tốt cho trẻ biếng ăn, còi xương hoặc phụ nữ sau sinh…', " +
                "'https://www.youtube.com/watch?v=R55KmEo_BZw', " +
                "'Khi mua ếch nên chọn ếch đồng tự nhiên, không nên chọn ếch nuôi bởi vì tuy béo nhưng thịt mềm nhũn và vị thịt thường rất nhạt nhẽo','stir_fry')," +

                "('stir_fry_4', 'Mì xào bò', 3.5, 'stir_fry_4.png', " +
                "'Mì xào vốn là một món ăn không quá xa lạ và thường xuyên được ăn kèm với nhiều món khác nhau. Những sợi mì dai dai cùng với thịt bò mềm, thơm sẽ là tổ hợp tuyệt vời cho bữa ăn đủ dưỡng chất và năng lượng cho cơ thể.', " +
                "'https://m.youtube.com/watch?v=F2yZWskGOfY&pp=0gcJCdgAo7VqN5tD', " +
                "'Nếu bạn thích ăn rau nhừ và thịt tái vừa thì cho rau vào xào trước, hoặc ngược lại tùy khẩu vị. Thịt bò đừng xào lâu quá thịt sẽ bị dai','stir_fry')," +

                "('stir_fry_5', 'Sườn rim sốt cà chua', 4.0, 'stir_fry_5.png', " +
                "'Sườn xào chua ngọt với cà chua có màu sắc đẹp mắt, vị chua thanh và thơm từ cà chua kết hợp với vị mặn ngọt vừa ăn thấm trong miếng sườn vô cùng bắt cơm. Chỉ cần 40 phút là bạn đã thực hiện xong cách làm sườn xào chua ngọt đơn giản, ngon bất bại, cùng bắt đầu ngay nhé!', " +
                "'https://www.youtube.com/watch?v=EgyCgd0K6oE', " +
                "'Luộc sơ sườn với gừng để khử mùi, chiên sơ cho săn lại trước khi rim. Xào cà chua riêng để dậy mùi, dùng cà chua chín đỏ giúp màu đẹp. Rim lửa nhỏ, nêm nước mắm và đường sau cùng, có thể thêm tương cà hoặc mật ong cho sốt sánh và ngon hơn','stir_fry')," +

                "('stir_fry_6', 'Bí đao xào đậu', 3.0, 'stir_fry_6.png', " +
                "'Bí đao là loại rau củ quen thuộc trong ẩm thực Việt, có vị ngọt nhẹ, tính mát và rất dễ chế biến. Các món ăn từ bí như canh bí, bí xào tỏi hay bí hấp không chỉ thơm ngon mà còn tốt cho sức khỏe, phù hợp cho cả người lớn và trẻ em.', " +
                "'https://cookpad.com/vn/cong-thuc/3180406', " +
                "'Nên chọn bí xanh to, chắc tay, vỏ xanh mướt và cuống còn tươi để có vị ngọt ngon. Tránh chọn quả quá già, vỏ xanh đậm hoặc đen vì thịt cứng, nhiều hạt và có vị chua, ảnh hưởng đến món ăn','stir_fry')," +

                "('stir_fry_7', 'Lươn xào', 3.5, 'stir_fry_7.png', " +
                "'Lươn xào sả ớt là món ăn đậm đà, cay thơm, rất đưa cơm. Thịt lươn mềm ngọt kết hợp vị cay nồng của ớt, hương thơm đặc trưng từ sả tạo nên hương vị khó quên. Đây là món ăn dân dã nhưng giàu dinh dưỡng, phù hợp cho bữa cơm gia đình thêm ấm cúng.', " +
                "'https://www.youtube.com/watch?v=NtHslFfnE3Y&pp=0gcJCdgAo7VqN5tD', " +
                "'Nên chọn lươn đồng vì thịt ngọt và săn chắc hơn lươn nuôi. Lươn ngon thường bụng vàng, lưng đen rõ, kích thước vừa phải. Tránh chọn lươn quá nhỏ hoặc quá to vì dễ là lươn nuôi, thịt nhão và kém thơm','stir_fry')," +

                "('stir_fry_8', 'Mực xào nấm đùi gà', 2.0, 'stir_fry_8.png', " +
                "'Mực xào nấm đùi gà là món ăn thơm ngon, dễ làm, phù hợp cho bữa cơm gia đình. Cách chế biến đơn giản nhưng vẫn giữ trọn vị tươi ngon của mực và độ dai giòn của nấm.', " +
                "'https://www.bachhoaxanh.com/kinh-nghiem-hay/bat-mi-cach-lam-muc-xao-nam-dui-ga-ngon-gion-ngot-1575195', " +
                "'Món này ngon nhất khi ăn nóng, có thể dùng kèm với cơm trắng hoặc bún tươi. Phi thơm tỏi, nêm gia vị vừa đủ để giữ vị ngọt của mực và độ béo của nấm.','stir_fry')," +

                "('stir_fry_9', 'Tôm rang tỏi', 2.0, 'stir_fry_9.png', " +
                "'Tôm là nguyên liệu quen thuộc, dễ chế biến và giàu dinh dưỡng. Trong đó, tôm rang tỏi được nhiều người ưa thích nhờ cách làm đơn giản nhưng hương vị thơm ngon, đậm đà.', " +
                "'https://www.youtube.com/watch?v=l0xqi4OhY2g', " +
                "'Nên chọn tôm tươi, vỏ còn bóng và chắc. Trước khi rang, ướp tôm với chút nước mắm và đường để thấm vị. Phi tỏi thật vàng rồi mới cho tôm vào giúp món ăn dậy mùi và có màu đẹp mắt hơn','stir_fry')," +

                "('stir_fry_10', 'Bông cải xào thịt bò', 3.5, 'stir_fry_10.png', " +
                "'Thịt bò có hương vị thơm ngon đặc trưng và cung cấp nhiều chất dinh dưỡng. Súp lơ giúp ngăn ngừa loãng xương, cải thiện sức đề kháng, là sự kết hợp lý tưởng.', " +
                "'https://www.youtube.com/watch?v=I-Ah2OmZQc8', " +
                "'Chọn thịt bò màu đỏ sẫm, không có mùi lạ, đàn hồi tốt. Chọn súp lơ đúng mùa, màu sáng, thân chắc và hoa dính chặt để đảm bảo độ tươi ngon.','stir_fry');";

        databaseSQLite.QuerySQL(INSERT_STIR_FRY_FOOD);

        /*============================================================================================================*/

        String INSERT_BRAISE_FOOD = "INSERT OR IGNORE INTO Food VALUES " +
                "('braise_1', 'Thịt kho trứng', 3.75, 'braise_1.png', " +
                "'Thịt kho tàu hay còn gọi là thịt kho trứng, thịt kho hột vịt là một món ăn không thể thiếu trong mỗi gia đình trong dịp Tết. Vị thơm của thịt, vị ngọt của nước thịt ăn kèm với một ít củ kiệu thì sẽ không có món ăn nào ngon bằng trong những ngày cả gia đình quây quần bên nhau.', " +
                "'https://www.youtube.com/watch?v=96lyMIrvhOo', " +
                "'Ướp thịt trước để thấm gia vị, nên dùng nước dừa tươi và kho lửa nhỏ để thịt mềm, trứng thấm vị và không bị vỡ. Chiên trứng trước giúp trứng săn chắc và ngon hơn','braise')," +

                "('braise_2', 'Thịt kho tiêu', 3.75, 'braise_2.png', " +
                "'Thịt ba chỉ kho tiêu xanh là một trong những món kho hao cơm được nhiều gia đình Việt ưa thích cho bữa cơm gia đình. Các món kho tiêu có mùi thơm nức mũi, kích thích vị giác sẽ làm cho bữa cơm gia đình thêm ấm cúng.', " +
                "'https://www.youtube.com/watch?v=P2w9e6xNiB8', " +
                "'Chọn thịt ba chỉ hồng tươi, đàn hồi tốt, không mùi lạ, không nhớt. Ưu tiên miếng nạc – mỡ cân đối, da dày vừa, thịt dính liền mỡ để kho thơm ngon, béo mềm','braise')," +

                "('braise_3', 'Cá chép kho tộ', 3.75, 'braise_3.png', " +
                "'Cá chép kho tộ là một món ăn dân dã nhưng lại vô cùng thơm ngon. Cá chép kho tộ không còn xa lạ đối với nhiều người, là một món ăn khá dễ làm và nguyên liệu rất dễ kiếm.', " +
                "'https://www.bachhoaxanh.com/kinh-nghiem-hay/ca-chep-kho-to-ngon-duom-vi-ben-com-vo-cung-1191748', " +
                "'Nên chọn cá dày mình đều, không chỉ dày bụng – thịt sẽ ngọt và chắc hơn. Tránh mua cá quá to, vì thường nhiều mỡ, ruột lớn, thịt bở và không thơm do nuôi công nghiệp','braise')," +

                "('braise_4', 'Đậu hũ nhồi thịt', 3.75, 'braise_4.png', " +
                "'Đậu hũ nhồi thịt là một món ăn phổ biến trong ẩm thực Việt Nam, kết hợp vị béo của đậu hũ và vị đậm đà của thịt. Món này có thể chế biến theo nhiều cách khác nhau như chiên, hấp, hoặc sốt cà chua, đều mang đến hương vị thơm ngon, hấp dẫn.', " +
                "'https://www.youtube.com/watch?v=_TNFP5xylG0', " +
                "'Đậu hũ nhồi thịt là món ăn dân dã nhưng đậm đà, dễ ăn, đặc biệt khi chiên vàng giòn lớp vỏ ngoài. Nếu kết hợp thêm sốt cà chua chua nhẹ, thơm lừng, món ăn sẽ càng hấp dẫn, đậm vị và bắt cơm hơn','braise')," +

                "('braise_5', 'Thịt kho trứng cút', 3.5, 'braise_5.png', " +
                "'Thịt kho trứng cút có hương vị không khác món thịt kho trứng thế nhưng sử dụng trứng cút sẽ đem lại một sự mới lạ. Trứng cút nhỏ ngon miệng và dễ ăn nên các bé cũng sẽ thích hơn đấy.', " +
                "'https://www.youtube.com/watch?v=Ef2KDf7x1BY&pp=0gcJCfwAo7VqN5tD', " +
                "'Ướp thịt kỹ trước khi kho để thấm vị, chiên sơ trứng cút giúp trứng săn chắc và không bị bể khi kho. Dùng nước dừa tươi để món ăn có màu đẹp, vị ngọt thanh và thơm hơn','braise')," +

                "('braise_6', 'Gà kho nghệ', 3.5, 'braise_6.png', " +
                "'Thịt gà mềm, dai kết hợp với nghệ tạo nên một món ăn có hương vị đặc trưng và có màu sắc bắt mắt. Vì được nấu với nghệ tươi, món ăn còn tốt cho tiêu hóa.', " +
                "'https://www.youtube.com/watch?v=-vqkGWJ7YT8', " +
                "'Món gà kho nghệ sẽ ngon hơn khi ăn nóng, ăn kèm cơm trắng, dưa leo và rau sống sẽ càng tuyệt vời.','braise')," +

                "('braise_7', 'Cá nục kho sốt cà chua', 3.5, 'braise_7.png', " +
                "'Cá nục kho sốt cà chua là một món ăn phổ biến, đậm đà hương vị, với cá nục thơm ngon được kho cùng sốt cà chua chua ngọt, rất thích hợp để ăn cùng cơm nóng.', " +
                "'https://www.youtube.com/watch?v=XoxCH_WlpLI', " +
                "'Nên chọn cá nục tươi, thân thon dài, mắt trong. Chiên sơ cá giúp thịt săn chắc, không tanh. Phi hành tỏi với cà chua chín mềm để tạo màu và hương vị đậm đà','braise')," +

                "('braise_8', 'Sườn non kho tiêu', 3.0, 'braise_8.png', " +
                "'Sườn non kho tiêu là món ăn truyền thống quen thuộc, nổi bật với vị mặn ngọt đậm đà, thơm nồng hương tiêu và nước mắm. Món ăn có màu nâu óng đẹp mắt.', " +
                "'https://www.youtube.com/watch?v=1hNyFSalLNg', " +
                "'Kho hai lửa: đầu lửa mạnh để săn sườn, sau lửa nhỏ để thấm gia vị. Dùng nồi đất hoặc đáy dày giữ nhiệt tốt và giúp lên màu đẹp. Múc nước sốt tưới lên sườn thường xuyên','braise')," +

                "('braise_9', 'Vịt kho sả ớt', 3.5, 'braise_9.png', " +
                "'Vịt kho sả ớt là món ăn đậm đà, cay nồng của miền Nam, kết hợp vị béo của vịt với mùi thơm đặc trưng của sả, tỏi, ớt. Thịt vịt mềm rục, thấm đều nước sốt.', " +
                "'https://www.youtube.com/watch?v=e2aVrzaAAK4', " +
                "'Khử mùi vịt với rượu trắng hoặc chanh + gừng. Kho vừa tới khi nước sệt, không khô quá để thịt mềm và thấm vị.','braise')," +

                "('braise_10', 'Ba chỉ kho nước mắm', 2.5, 'braise_10.png', " +
                "'Ba chỉ kho nước mắm là món truyền thống với vị mặn – ngọt – béo hòa quyện, thịt mềm mọng, thấm nước mắm, thêm hành tỏi phi thơm – màu sắc bắt mắt.', " +
                "'https://www.youtube.com/watch?v=FXdDUUj9K4M', " +
                "'Chọn thịt có nạc mỡ cân đối, thắng đường đúng độ. Kho liu riu, lật thịt đều để thấm màu. Thêm nước dừa để tạo vị ngọt tự nhiên và giảm độ mặn.','braise')";

        databaseSQLite.QuerySQL(INSERT_BRAISE_FOOD);

        /*============================================================================================================*/
        String INSERT_SOUP_FOOD = "INSERT OR IGNORE INTO Food VALUES " +
                "('soup_1', 'Canh chua', 2.0, 'soup_1.png', " +
                "'Canh chua là món ăn tinh túy của ẩm thực Việt Nam, nổi bật với hương vị chua thanh và đậm đà.', " +
                "'https://www.youtube.com/watch?v=7YLxmp8Vg6I', " +
                "'Chọn cá lóc tươi, sơ chế cá kỹ để khử tanh và chú ý lửa khi nấu','soup')," +

                "('soup_2', 'Canh rau muống luộc', 0.5, 'soup_2.png', " +
                "'Món ăn dân dã, quen thuộc trong bữa cơm gia đình Việt, đặc biệt là vào mùa hè', " +
                "'https://www.youtube.com/watch?v=RuJ4DYbsKbs', " +
                "'Chọn rau muống có kích thước vừa phải (như đũa ăn), hạn chế mua cọng to bất thường','soup')," +

                "('soup_3', 'Canh cà chua trứng', 1.0, 'soup_3.png', " +
                "'Canh cà chua là món truyền thống với nguyên liệu đơn giản, dễ làm, vị thanh nhẹ, ngọt dịu từ cà chua và trứng.', " +
                "'https://www.youtube.com/watch?v=wIkF-5yqj5w', " +
                "'Chọn cà chua chín đỏ mọng, xào kỹ để nước ngọt và đẹp, nêm nhẹ tránh quá mặn','soup')," +

                "('soup_4', 'Canh sườn bò', 2.0, 'soup_4.png', " +
                "'Canh sườn bò (Galbitang – Hàn Quốc) với sườn hầm mềm, củ cải trắng, hành boa rô và nấm, vị thanh đậm đà.', " +
                "'https://www.youtube.com/watch?v=9aViMyfonCc', " +
                "'Chần sườn bò giúp nước trong, nấu theo thứ tự: sườn, củ cải ,rau, nêm nhẹ để giữ vị ngọt từ xương','soup')," +

                "('soup_5', 'Canh rau ngót', 2.0, 'soup_5.png', " +
                "'Canh rau ngót là món canh quen thuộc, ngọt thanh, nhẹ nhàng và bổ dưỡng, phù hợp ngày hè.', " +
                "'https://www.youtube.com/watch?v=8_GX9p8LPi8', " +
                "'Vò rau nhẹ, không nấu quá lâu. Xào thịt sơ, nêm nhẹ, vớt bọt để nước trong.','soup')," +

                "('soup_6', 'Canh xương khoai tây', 2.0, 'soup_6.png', " +
                "'Canh xương khoai tây truyền thống, ngọt từ xương heo và rau củ, thơm dịu, dễ ăn.', " +
                "'https://www.youtube.com/watch?v=TpFevOYafKg', " +
                "'Vớt bọt thường xuyên, không chần xương quá lâu, không nấu khoai quá chín tránh bị nát','soup')," +

                "('soup_7', 'Canh bí đỏ thịt bằm', 2.0, 'soup_7.png', " +
                "'Canh bí đỏ thịt bằm thanh mát, bổ dưỡng, dễ làm, phù hợp cho trẻ nhỏ và người lớn tuổi.', " +
                "'https://www.youtube.com/watch?v=NfCEkUrCozM', " +
                "'Chọn bí đỏ chắc vỏ, không nấu quá lâu, vớt bọt giúp canh trong và đẹp hơn','soup')," +

                "('soup_8', 'Canh khổ qua nhồi thịt', 3.0, 'soup_8.png', " +
                "'Canh khổ qua nhồi thịt truyền thống ngày Tết, thanh mát, hơi đắng hòa quyện vị ngọt của nhân thịt.', " +
                "'https://www.youtube.com/watch?v=dGlLAmrnPlM', " +
                "'Nhồi vừa phải để không rơi nhân, nấu lửa lớn giúp giữ màu xanh đẹp','soup')," +

                "('soup_9', 'Canh cua rau muống', 2.0, 'soup_9.png', " +
                "'Canh cua rau muống thanh mát, ngọt tự nhiên từ cua đồng, xanh giòn của rau muống – chuẩn vị đồng quê.', " +
                "'https://www.youtube.com/watch?v=EiNlHeyYbIY', " +
                "'Dùng cua đồng tươi, chưng gạch trước, không nấu rau quá lâu để giữ màu và độ giòn','soup')," +

                "('soup_10', 'Canh rau tía tô', 2.0, 'soup_10.png', " +
                "'Canh tía tô nhẹ nhàng, thơm thảo dược, giải cảm, tốt cho hệ hô hấp và tiêu hóa.', " +
                "'https://www.youtube.com/watch?v=cV-vsB8hz3k', " +
                "'Dùng lá tía tô tươi, không nấu quá lâu để giữ màu và hương vị. Xào sơ thịt với cà chua để nước đẹp','soup');";

        databaseSQLite.QuerySQL(INSERT_SOUP_FOOD);


        String INSERT_FRY_FOOD = "INSERT OR IGNORE INTO Food VALUES " +
                "('fry_1', 'Trứng chiên', 1.5, 'fry_1.png', " +
                "'Trứng chiên là món ăn phổ biến trong bữa sáng hoặc bữa phụ, được nhiều người Việt yêu thích bởi độ dễ làm, thơm ngon, và có thể kết hợp linh hoạt với các nguyên liệu đi kèm như thịt bằm, hành lá, xúc xích…', " +
                "'https://www.youtube.com/watch?v=JvSgS_-cprw', " +
                "'Chọn trứng tươi, đánh kỹ nhưng không tạo bọt. Dầu nóng mới đổ trứng để lớp vỏ giòn, không dính','fry')," +

                "('fry_2', 'Cơm chiên', 2.0, 'fry_2.png', " +
                "'Cơm chiên là món ăn phổ biến, tiện lợi và giàu hương vị. Kết hợp cơm nguội với trứng, xúc xích, rau củ, tạo nên món ăn mặn ngọt hài hòa.', " +
                "'https://www.youtube.com/watch?v=iTg-z-INLPk', " +
                "'Dùng cơm nguội giúp cơm tơi, chiên dễ. Thêm bơ + dầu tạo vị béo. Lửa lớn, đảo từng đợt để cơm giòn tự nhiên','fry')," +

                "('fry_3', 'Nem chiên (Ram)', 3.0, 'fry_3.png', " +
                "'Nem chiên – món truyền thống ngày lễ, giòn bên ngoài, nhân thịt – miến – rau củ ngọt mềm bên trong. Ăn kèm rau sống, nước chấm chua ngọt.', " +
                "'https://www.youtube.com/watch?v=ReStezj6EzA', " +
                "'Nhân khô ráo, vỏ phết giấm để giòn lâu. Gói không quá chặt để tránh nem vỡ khi chiên','fry')," +

                "('fry_4', 'Trứng chiên thịt bằm', 2.0, 'fry_4.png', " +
                "'Món ăn nhanh, bổ dưỡng, hợp mọi bữa. Trứng mềm, thịt thơm, gia vị đậm đà, dễ ăn và hấp dẫn.', " +
                "'https://www.youtube.com/watch?v=3_hwleyZPb4', " +
                "'Không đánh quá bọt. Xào thịt trước cho thơm. Dùng chảo nóng, đậy nắp giúp trứng nở, vàng đều','fry')," +

                "('fry_5', 'Trứng chiên cà chua', 2.0, 'fry_5.png', " +
                "'Trứng mềm béo kết hợp vị chua ngọt của cà chua, đơn giản mà mới lạ, dễ chế biến, phù hợp mọi bữa.', " +
                "'https://www.youtube.com/watch?v=8TA35kjFOZQ', " +
                "'Đánh trứng vừa phải, dùng dầu mè/dầu điều cho màu đẹp. Chiên lửa vừa, đậy nắp giữ ẩm','fry')," +

                "('fry_6', 'Cánh gà chiên nước mắm', 2.5, 'fry_6.png', " +
                "'Cánh gà giòn bên ngoài, mềm bên trong, thấm vị nước mắm tỏi ớt mặn – ngọt – cay, cực kỳ hấp dẫn.', " +
                "'https://www.youtube.com/watch?v=ozNNdCjKQzM', " +
                "'Nước sốt: mắm + đường + dầu hào + tương ớt. Khử mùi bằng chanh, giấm, gừng hoặc muối.','fry')," +

                "('fry_7', 'Khoai tây chiên', 2.0, 'fry_7.png', " +
                "'Món ăn vặt/ăn kèm ''quốc dân'', giòn rụm vàng đều nếu làm đúng cách, rất đưa cơm hoặc dùng khai vị.', " +
                "'https://www.youtube.com/watch?v=G815Ub7YB_E', " +
                "'Phơi khoai thật khô trước khi chiên. Chiên hai lần: lần đầu lửa vừa – lần hai lửa lớn giúp vỏ giòn, ruột mềm','fry')," +

                "('fry_8', 'Cá chiên', 2.0, 'fry_8.png', " +
                "'Cá chiên với da giòn, thịt ngọt, là món quen thuộc trong bữa cơm Việt. Phù hợp với mọi loại cá.', " +
                "'https://www.youtube.com/watch?v=LuthIO83abw', " +
                "'Lau khô cá để tránh bắn dầu. Chiên lửa vừa, lật cá khi đã vàng đều để giữ nguyên lớp da giòn','fry')," +

                "('fry_9', 'Chả chiên', 2.0, 'fry_9.png', " +
                "'Chả chiên từ thịt xay, giòn vỏ – mềm ruột, món ăn cơ bản nhưng cực kỳ đưa cơm, dùng được với cơm, bún, bánh mì.', " +
                "'https://www.youtube.com/watch?v=Htcw4HiBbvM', " +
                "'Nhồi kỹ cho dẻo, chiên hai lần để giòn. Dùng bột chiên hoặc bột bắp giúp giữ nước, chả không bị khô','fry');";

        databaseSQLite.QuerySQL(INSERT_FRY_FOOD);
        String INSERT_DESSERT_FOOD = "INSERT OR IGNORE INTO Food VALUES " +
                "('dessert_1', 'Bánh chưng', 4.0, 'dessert_1.png', " +
                "'Bánh chưng là biểu tượng Tết cổ truyền, gói bằng lá dong, nhân thịt đậu xanh, tượng trưng cho đất trời và sự sum vầy.', " +
                "'https://www.youtube.com/watch?v=FbRuScitv88', " +
                "'Dùng gạo nếp ngon, luộc sơ lá dong. Buộc chặt tay, giữ nước sôi đều, không đổ nước lạnh để tránh nát nhân','dessert')," +

                "('dessert_2', 'Bánh tét', 4.0, 'dessert_2.png', " +
                "'Bánh Tét – đặc trưng Tết miền Nam, hình trụ dài, gói bằng lá chuối, nhân thịt hoặc chuối, tượng trưng ấm no, đoàn tụ.', " +
                "'https://www.youtube.com/watch?v=ipIItspsDe8', " +
                "'Giữ nước sôi liu riu, không thay nước lạnh. Buộc chắc tay. Dùng lá chuối trụng mềm giúp dễ bó và bánh xanh đẹp','dessert')," +

                "('dessert_3', 'Bánh dày', 2.0, 'dessert_3.png', " +
                "'Bánh dày – món truyền thống tượng trưng cho trời, ăn kèm giò chả, đặc biệt dịp giỗ tổ, lễ Tết.', " +
                "'https://www.youtube.com/watch?v=4QVaSE9v7RE', " +
                "'Dùng bột mới, thêm ít bột năng cho mềm dẻo. Hấp đến khi bánh chuyển màu đục. Phủ dầu chống dính','dessert')," +

                "('dessert_4', 'Bánh ú', 4.0, 'dessert_4.png', " +
                "'Bánh ú thường dùng trong Tết Đoan Ngọ, hình chóp, nhân đậu xanh hoặc mặn, gói trong lá chuối/lá tre thơm.', " +
                "'https://www.youtube.com/watch?v=ImQdcnopv2o', " +
                "'Ngâm gạo với nước tro, buộc chặt tay, luộc lửa nhỏ đủ lâu. Châm thêm nước sôi khi cần để bánh dẻo và trong','dessert')," +

                "('dessert_5', 'Bánh bèo', 2.0, 'dessert_5.png', " +
                "'Bánh bèo Huế mềm mịn, nhân tôm, mỡ hành, chan nước mắm ngọt – món khai vị hấp dẫn miền Trung.', " +
                "'https://www.youtube.com/watch?v=m53BA727x_c', " +
                "'Phết dầu vào chén để dễ lấy. Đổ lượng bột vừa đủ, hấp đến khi có xoáy. Rải nhân đầy, tạo vị béo, thơm','dessert')," +

                "('dessert_6', 'Bánh trôi nước', 2.0, 'dessert_6.png', " +
                "'Bánh trôi nước – món tráng miệng truyền thống, nhân đường hoặc đậu xanh, chan nước gừng, thường dùng Tết Hàn thực.', " +
                "'https://www.youtube.com/watch?v=KjuRVV2Wrl8', " +
                "'Ủ bột kỹ để không nứt vỏ. Bánh nổi thì vớt ngâm nước lạnh. Có thể pha màu tự nhiên làm bánh ngũ sắc','dessert')," +

                "('dessert_7', 'Bánh bao nhân thịt', 2.5, 'dessert_7.png', " +
                "'Bánh bao nhân thịt, trứng, mộc nhĩ – món ăn tiện lợi, mềm mịn, ngon miệng cho bữa sáng hoặc ăn vặt.', " +
                "'https://www.youtube.com/watch?v=eqFymuP79f8', " +
                "'Ủ đúng thời gian, che khăn khi hấp để tránh nước đọng. Không để bột nghỉ quá lâu tránh vỏ chai','dessert')," +

                "('dessert_8', 'Bánh chuối chiên', 1.0, 'dessert_8.png', " +
                "'Bánh chuối chiên – món ăn vặt đường phố, giòn rụm bên ngoài, ngọt mềm bên trong.', " +
                "'https://www.youtube.com/watch?v=VyQ-QP3Svo4', " +
                "'Ép chuối cho đẹp. Chiên 2 lần để giòn lâu. Thêm mè vào bột và điều chỉnh độ sánh hợp lý','dessert')," +

                "('dessert_9', 'Bánh Flan', 1.5, 'dessert_9.png', " +
                "'Bánh flan – món tráng miệng sữa trứng mềm mịn, phủ caramel đắng nhẹ, rất được yêu thích.', " +
                "'https://www.youtube.com/watch?v=hrxrDlyddbY', " +
                "'Rây hỗn hợp kỹ, hấp cách thủy nhẹ, tránh bọt khí. Caramel vừa màu để không đắng','dessert')," +

                "('dessert_10', 'Bánh Gato', 2.0, 'dessert_10.png', " +
                "'Bánh Gato – cốt bông lan mềm xốp, dùng cho tiệc sinh nhật hoặc làm đế bánh kem.', " +
                "'https://www.youtube.com/watch?v=DJK1U4aXXEM', " +
                "'Rây bột mịn, trộn nhẹ tay giữ bọt khí. Luôn làm nóng lò và giữ nhiệt ổn định','dessert')," +

                "('dessert_11', 'Bánh kem', 3.0, 'dessert_11.png', " +
                "'Bánh kem – bánh sinh nhật phổ biến, với lớp kem tươi trang trí bắt mắt, làm từ cốt Gato.', " +
                "'https://www.youtube.com/watch?v=sx3sJXjrVNA', " +
                "'Đánh lòng trắng đúng độ. Trộn “fold” giữ khí. Dùng đĩa xoay phủ kem láng và đẹp','dessert');";

        databaseSQLite.QuerySQL(INSERT_DESSERT_FOOD);

        String INSERT_FOREIGN_FOOD = "INSERT OR IGNORE INTO Food VALUES " +
                "('foreign_1', 'Mỳ ý', 2.0, 'foreign_1.png', " +
                "'Mì Ý là món quốc tế phổ biến ở Việt Nam với nhiều loại sốt như Bolognese, kem, hải sản – dễ làm và phù hợp mọi bữa.', " +
                "'https://www.youtube.com/watch?v=NyHb_E8C74w', " +
                "'Sốt Bolognese: thịt bò + cà chua + rau củ. Vongole: nghêu, vang trắng, tỏi. Pomodoro: cà chua, húng quế, bơ','foreign')," +

                "('foreign_2', 'Pizza', 2.0, 'foreign_2.png', " +
                "'Pizza – món Ý với đế bánh mềm giòn, sốt cà, phô mai và nhiều topping – phù hợp mọi bữa và sở thích.', " +
                "'https://www.youtube.com/watch?v=MDX8fylSisU', " +
                "'Ủ bột lạnh qua đêm giúp thơm. Dùng đá pizza cho vỏ giòn. Margherita: cà, mozzarella, basil, dầu ô liu','foreign')," +

                "('foreign_3', 'Hamburger', 2.0, 'foreign_3.png', " +
                "'Hamburger – món ăn phương Tây phổ biến, gồm miếng thịt bò nướng kẹp giữa bánh mì, rau, sốt – đa dạng và tiện lợi.', " +
                "'https://www.youtube.com/watch?v=BIG1h2vG-Qg', " +
                "'Thịt bò xay 80/20 ngon mọng. Tạo lõm giữa pattie khi nướng. Không ép chặt thịt tránh bị dai. Dùng sốt từ nước thịt ra','foreign')," +

                "('foreign_4', 'Takoyaki', 3.0, 'foreign_4.png', " +
                "'Takoyaki – bánh bạch tuộc chiên tròn từ Nhật, giòn ngoài mềm trong, nhân bạch tuộc và vụn tempura, rất hấp dẫn.', " +
                "'https://www.youtube.com/watch?v=1i9pKIAGxD8', " +
                "'Tránh đổ bột quá đầy. Quay đều tay để bánh tròn đẹp. Có thể thay nhân: tôm, xúc xích, kimchi...','foreign')," +

                "('foreign_5', 'Sushi', 2.0, 'foreign_5.png', " +
                "'Sushi – món Nhật tinh tế với cơm giấm và topping như cá sống, trứng, rong biển, được cuốn hoặc nặn tỉ mỉ.', " +
                "'https://www.youtube.com/watch?v=nIoOv6lWYnk', " +
                "'Không trộn mạnh tay để cơm không nát. Dùng makisu bọc nilon cuốn dễ. Chấm phần cá vào xì dầu, không nhúng cơm','foreign')," +

                "('foreign_6', 'Sashimi', 2.0, 'foreign_6.png', " +
                "'Sashimi – hải sản tươi sống cắt lát mỏng, không cơm, tập trung vào vị tinh khiết – đòi hỏi kỹ thuật cắt chuyên nghiệp.', " +
                "'https://www.youtube.com/watch?v=eSpkVJEai48', " +
                "'Dao phải thật sắc, cắt lát mịn. Nhúng dao giữa mỗi lần cắt. Nếu không dùng sashimi-grade, cần cấp đông -20°C trước','foreign')," +

                "('foreign_7', 'Omelet', 1.5, 'foreign_7.png', " +
                "'Omelet – trứng rán gập kiểu Pháp, vàng nhẹ ngoài, mềm bên trong – biến tấu với phô mai, rau, thịt tùy ý.', " +
                "'https://www.youtube.com/watch?v=l1vus_xR1a8', " +
                "'Không đánh kỹ để giữ độ mịn. Chảo chống dính đúng nhiệt. Thêm bơ lạnh khi gập giúp láng mượt','foreign')," +

                "('foreign_8', 'Cơm nắm', 1.0, 'foreign_8.png', " +
                "'Cơm nắm – món ăn tiện gọn, có thể cho vào hộp mang đi, dùng nóng hoặc nguội. Phiên bản Nhật là onigiri.', " +
                "'https://www.youtube.com/watch?v=5Q0mLEWXfiA', " +
                "'Dùng gạo dẻo. Nắm lúc cơm còn ấm. Dùng găng tay thấm giấm để dễ nắm và không dính tay','foreign')," +

                "('foreign_9', 'Tacos', 2.0, 'foreign_9.png', " +
                "'Tacos – món ăn đường phố Mexico, bánh tortilla kẹp thịt, rau và sốt – đậm vị, dễ ăn, nhiều kiểu chế biến.', " +
                "'https://www.youtube.com/watch?v=emgRgUctsqg', " +
                "'Giữ thịt ẩm, tránh xào khô. Làm ấm tortilla trước để không vỡ. Tự làm sốt: jalapeño + cà chua + tỏi xay','foreign')," +

                "('foreign_10', 'Gà nướng đất sét', 3.0, 'foreign_10.png', " +
                "'Gà nướng đất sét – món dân dã độc đáo: gà bọc đất nướng chậm, giữ ẩm mềm và thơm mùi thảo mộc.', " +
                "'https://www.youtube.com/watch?v=ocLv29Xvh8w', " +
                "'Ướp kỹ, quay đều khi nướng. Phủ đất 3–5mm. Nướng lò: 120°C 2h hơi ẩm, rồi 200°C làm giòn da','foreign');";

        databaseSQLite.QuerySQL(INSERT_FOREIGN_FOOD);
    }

    // Them Dl cho bang Tag
    public static void InsertData_Tag(Context context) {
        DatabaseSQLite databaseSQLite = new DatabaseSQLite(context, "APP_TEACHING_COOK.sqlite", null, 1);

        String INSERT_TAG = "INSERT OR IGNORE INTO Tag VALUES " +
                "('stir_fry','Xào','null')," +
                "('braise', 'Kho','null')," +
                "('soup', 'Canh','null')," +
                "('fry', 'Chiên','null')," +
                "('dessert', 'Bánh','null')," +
                "('foreign', 'Khác','null');";
        databaseSQLite.QuerySQL(INSERT_TAG);
    }

    /*Thêm dữ liệu vào Ingredients*/
    public static void InsertData_Ingredients(Context context) {
        DatabaseSQLite databaseSQLite = new DatabaseSQLite(context, "APP_TEACHING_COOK.sqlite", null, 1);

        String INSERT_INGREDIENTS_STIR_FRY = "INSERT OR IGNORE INTO Ingredients VALUES " +
                "(1, 'stir_fry_1', '1 bó rau muống')," +
                "(2, 'stir_fry_1', '2 - 3 củ tỏi')," +
                "(3, 'stir_fry_1', 'Gia vị: Dầu ăn, nước mắm, đường, hạt nêm, tiêu.')," +

                "(1, 'stir_fry_2', '500g thịt hến')," +
                "(2, 'stir_fry_2', '500g giá đỗ')," +
                "(3, 'stir_fry_2', '300g hẹ')," +
                "(4, 'stir_fry_2', 'Tỏi, hành tím')," +
                "(5, 'stir_fry_2', 'Gia vị: Dầu ăn, muối, đường, hạt nêm, tiêu xay')," +

                "(1, 'stir_fry_3', '400g Thịt ếch')," +
                "(2, 'stir_fry_3', '2 trái cà tím')," +
                "(3, 'stir_fry_3', 'Sả, gừng, ngò gai')," +
                "(4, 'stir_fry_3', 'Sa tế, dầu ăn, tiêu, hạt nêm, bột ngọt')," +

                "(1, 'stir_fry_4', '400gr thịt bò, 500gr cải thìa')," +
                "(2, 'stir_fry_4', 'Mì tôm')," +
                "(3, 'stir_fry_4', 'Rau ngò, tỏi, hành, bột bắp (nếu thích)')," +
                "(4, 'stir_fry_4', 'Gia vị: Tiêu, dầu ăn, hạt nêm, nước tương, đường')," +

                "(1, 'stir_fry_5', '500g sườn non')," +
                "(2, 'stir_fry_5', '2 - 3 quả cà chua chín tùy kích cỡ')," +
                "(3, 'stir_fry_5', 'Hành lá, tỏi băm')," +
                "(4, 'stir_fry_5', 'Gia vị: hạt nêm, đường, tương ớt, tiêu, dầu ăn')," +

                "(1, 'stir_fry_6', '1 khúc bí xanh, đậu phộng')," +
                "(2, 'stir_fry_6', 'Ớt, tiêu, tỏi')," +
                "(3, 'stir_fry_6', 'Gia vị: bột ngọt, bột canh, dầu ăn, mắm tôm')," +

                "(1, 'stir_fry_7', '600g thịt lươn làm sẵn')," +
                "(2, 'stir_fry_7', 'Rau răm, sả, ớt, hành tăm')," +
                "(3, 'stir_fry_7', 'Gia vị: Ớt bột, dầu ăn, dầu màu điều, nước mắm, tiêu xay')," +

                "(1, 'stir_fry_8', '400g mực, 100g nấm đùi gà')," +
                "(2, 'stir_fry_8', 'Nấm hương khô, nấm đông cô khô')," +
                "(3, 'stir_fry_8', 'Hành lá, ớt, tỏi')," +
                "(4, 'stir_fry_8', 'Gia vị: Muối, đường, bột ngọt, hạt nêm, dầu hào, tương ớt, tiêu, ớt xay, dầu ăn')," +

                "(1, 'stir_fry_10', '300g bông cải (súp lơ), 200g thịt bò')," +
                "(2, 'stir_fry_10', 'Hành tây, hành lá, tỏi')," +
                "(3, 'stir_fry_10', 'Gia vị: đường, muối, nước mắm, tiêu, nước tương, bột ngọt')," +

                "(1, 'stir_fry_9', '500g tôm tươi')," +
                "(2, 'stir_fry_9', '1 củ tỏi')," +
                "(3, 'stir_fry_9', 'Gia vị: dầu ăn, nước mắm, hạt nêm, muối, đường');";

        databaseSQLite.QuerySQL(INSERT_INGREDIENTS_STIR_FRY);

        /*============================================================================================================*/
        String INSERT_INGREDIENTS_BRAISE = "INSERT OR IGNORE INTO Ingredients VALUES " +
                "(1, 'braise_1', '1kg thịt ba rọi, 8 quả trứng vị hoặc gà.')," +
                "(2, 'braise_1', '700ml nước dừa tươi, 600ml nước lọc, vài củ hành tím, tỏi, ớt sừng.')," +
                "(3, 'braise_1', 'Gia vị: hạt nêm, bột ngọt, nước mắm, nước màu đường, đường phèn, chanh.')," +

                "(1, 'braise_2', '200g thịt ba chỉ')," +
                "(2, 'braise_2', 'Tiêu xanh (hoặc hạt tiêu), hành tím, tỏi, hành lá, ớt')," +
                "(3, 'braise_2', 'Gia vị: Nước mắm,nước màu dừa, muối, đường, tiêu xay, bột canh nấm bào ngư, bột ngọt.')," +

                "(1, 'braise_3', '400g cá chép, 150g thịt ba chỉ')," +
                "(2, 'braise_3', 'Hành lá, tỏi')," +
                "(3, 'braise_3', 'Gia vị: dầu ăn, nước mắm, đường, tiêu')," +

                "(1, 'braise_4', '2 miếng đậu hũ, 200g thịt xay')," +
                "(2, 'braise_4', '50g nấm tai mèo (mộc nhĩ)')," +
                "(3, 'braise_4', 'Gia vị: bột nêm, bột ngọt, dầu ăn')," +

                "(1, 'braise_5', '300g thịt ba chỉ, 20 quả trứng cút')," +
                "(2, 'braise_5', 'Hành lá, ớt, 1 gói gia vị thịt kho, 1 muỗng cà phê nước màu')," +
                "(3, 'braise_5', 'Gia vị: Nước mắm, bột ngọt, tiêu xay, muối, dầu ăn')," +

                "(1, 'braise_6', '500g thịt gà')," +
                "(2, 'braise_6', 'Gừng, nghệ tươi, bột nghệ, tỏi')," +
                "(3, 'braise_6', 'Gia vị: muối, đường, bột ngọt, nước mắm, tiêu')," +

                "(1, 'braise_7', 'Cá nục suôn xanh')," +
                "(2, 'braise_7', 'Cà chua, mía, nước dừa')," +
                "(3, 'braise_7', 'Tỏi, ớt, hành tím')," +
                "(4, 'braise_7', 'Gia vị: muối, nước mắm, hạt nêm, bột ngọt, tương ớt, cà hộp (nếu có)')," +

                "(1, 'braise_8', 'Sườn non 500 g (hoặc 600g – tùy khẩu phần)')," +
                "(2, 'braise_8', 'Hành tím 2–3 củ, tỏi 4–5 tép, ớt tươi 1 quả')," +
                "(3, 'braise_8', 'Tiêu đen xay 1-2 muỗng cà phê, tiêu sọ/tiêu xanh nếu có 15–20 g')," +
                "(4, 'braise_8', 'Nước dừa tươi 150–200 ml')," +
                "(5, 'braise_8', 'Gia vị: nước mắm, đường, nước hàng (hoặc tự thắng đường), hạt nêm, muối, dầu ăn')," +

                "(1, 'braise_9', 'Vịt 1/2 con (khoảng 700–800g)')," +
                "(2, 'braise_9', 'Sả 3 cây; Ớt tươi 2–3 quả; Hành tím 2 củ; Tỏi 4 tép')," +
                "(3, 'braise_9', 'Rượu trắng 50ml (hoặc gừng tươi giã nhỏ 1 củ) để khử mùi vịt')," +
                "(4, 'braise_9', 'Gia vị: Nước mắm, đường, muối, hạt nêm, tiêu, dầu ăn, nước màu')," +

                "(1, 'braise_10', 'Thịt ba chỉ heo – 500 g')," +
                "(2, 'braise_10', 'Hành tím – 2–3 củ; Tỏi – 3–4 tép')," +
                "(3, 'braise_10', 'Nước mắm – 3–4 muỗng canh; Đường – 1 muỗng canh; Hạt nêm – ½ muỗng cà phê')," +
                "(4, 'braise_10', 'Tiêu xay và ớt tươi – tùy khẩu vị')," +
                "(5, 'braise_10', 'Dầu ăn – 2–3 muỗng canh; (Tuỳ chọn: nước dừa ½ chén để tạo vị ngọt tự nhiên')";

        databaseSQLite.QuerySQL(INSERT_INGREDIENTS_BRAISE);

        /*============================================================================================================*/
        String INSERT_INGREDIENTS_SOUP = "INSERT OR IGNORE INTO Ingredients VALUES " +
                "(1, 'soup_1', 'Cá lóc 1 con (khoảng 500g)')" + "," +
                "(2, 'soup_1', 'Cà chua 2 quả; Dứa (thơm) 1/4 quả; Ớt tươi 1 quả; Hành tím 2 củ')" + "," +
                "(3, 'soup_1', 'Giá đỗ 100g; Đậu bắp 100g; Bạc hà (dọc mùng) 100g; Ngò om, ngò gai 50g; Me chua 50g')" + "," +
                "(4, 'soup_1', 'Gia vị: Nước mắm, muối, đường, hạt nêm')" + "," +

                "(5, 'soup_2', '400 g Rau muống đã lặt rửa sạch')" + "," +
                "(6, 'soup_2', '1,5-2 L nước')" + "," +
                "(7, 'soup_2', '1,5 quả chanh')" + "," +
                "(8, 'soup_2', 'Gia vị: 3/4 muỗng ăn cơm muối, 1 muỗng ăn cơm bột ngọt')" + "," +

                "(9, 'soup_3', '2 quả cà chua chín')" + "," +
                "(10, 'soup_3', '2 quả trứng gà (hoặc thêm nếu muốn)')" + "," +
                "(11, 'soup_3', '1,5 lít nước')" + "," +
                "(12, 'soup_3', 'Hành tím 1 củ, hành lá 2 nhánh')" + "," +
                "(13, 'soup_3', 'Gia vị: 1 muỗng cà phê muối, 1/2 muỗng cà phê bột ngọt, 1 muỗng cà phê nước mắm, dầu ăn')" + "," +

                "(14, 'soup_4', 'Sườn bò (dẻ sườn) – 500 g')" + "," +
                "(15, 'soup_4', 'Củ cải trắng – 200–300 g')" + "," +
                "(16, 'soup_4', 'Nấm kim châm – 100 g; Boa rô (cần tây) – 50 g')" + "," +
                "(17, 'soup_4', 'Tỏi 2 củ; Hành tím 1 củ; Hành lá + hành tây + gừng (tùy chọn)')" + "," +
                "(18, 'soup_4', 'Gia vị: muối, nước tương/Maggi, đường, nước dùng Maggi hoặc bột nêm lẩu bò')" + "," +

                "(19, 'soup_5', '300 g rau ngót (lá non, không già, nhặt bỏ cuống)')" + "," +
                "(20, 'soup_5', '150–200 g thịt nạc băm (hoặc tôm, thịt bằm)')" + "," +
                "(21, 'soup_5', '2 củ hành khô (băm nhỏ)')" + "," +
                "(22, 'soup_5', '1 lít nước (hoặc nước dùng)')" + "," +
                "(23, 'soup_5', 'Gia vị: muối, nước mắm, hạt nêm, bột ngọt, tiêu xay, dầu ăn')" + "," +

                "(24, 'soup_6', 'Xương heo (xương sườn/tái): 300–500 g')" + "," +
                "(25, 'soup_6', 'Khoai tây: 3 củ (~500 g), Cà rốt: 1 củ (~150–200 g), Hành tím: 2 củ')" + "," +
                "(26, 'soup_6', 'Hành lá, ngò rí: vài nhánh, thái nhỏ')" + "," +
                "(27, 'soup_6', 'Gia vị: muối, hạt nêm, bột ngọt, tiêu xay, dầu ăn')" + "," +

                "(28, 'soup_7', 'Bí đỏ: 300–500 g, gọt vỏ, cắt miếng vừa ăn')" + "," +
                "(29, 'soup_7', 'Thịt heo bằm: 150–200 g')" + "," +
                "(30, 'soup_7', 'Hành tím: 1–2 củ, băm nhỏ')" + "," +
                "(31, 'soup_7', 'Hành lá, ngò rí: vài nhánh, thái nhỏ')" + "," +
                "(32, 'soup_7', 'Gia vị: muối, hạt nêm, bột ngọt, tiêu xay, dầu ăn, (nước mắm – tùy chọn)')" + "," +

                "(33, 'soup_8', 'Khổ qua (mướp đắng): 3–4 trái (~500g)')" + "," +
                "(34, 'soup_8', 'Thịt heo bằm: 200–300g')" + "," +
                "(35, 'soup_8', 'Mộc nhĩ (nấm mèo): 2 tai, ngâm nở và băm nhỏ')" + "," +
                "(36, 'soup_8', 'Miến khô: 20g (tùy chọn)')" + "," +
                "(37, 'soup_8', 'Hành tím: 1–2 củ, băm nhỏ')" + "," +
                "(38, 'soup_8', 'Hành lá, ngò rí: vài nhánh, thái nhỏ')" + "," +
                "(39, 'soup_8', 'Gia vị: muối, nước mắm, hạt nêm, bột ngọt, tiêu xay')" + "," +

                "(40, 'soup_9', 'Cua đồng: 300–400 g, làm sạch, giã nhuyễn, lọc lấy nước và giữ phần gạch')" + "," +
                "(41, 'soup_9', 'Rau muống: 1 bó (~300 g), nhặt ngọn non, rửa sạch, cắt khúc vừa ăn')" + "," +
                "(42, 'soup_9', 'Hành tím: 1–2 củ, băm nhỏ')" + "," +
                "(43, 'soup_9', 'Gia vị: muối, hạt nêm, bột ngọt, tiêu')" + "," +
                "(44, 'soup_9', 'Dầu ăn: để phi hành và gạch cua')" + "," +

                "(45, 'soup_10', 'Thịt nạc bằm: 150–200 g')" + "," +
                "(46, 'soup_10', 'Lá tía tô: 1–2 bó nhỏ (~50 g), nhặt, rửa sạch, thái khúc')" + "," +
                "(47, 'soup_10', 'Hành tím: 1 củ, Trứng: 1–2 quả; Cà chua: 1 quả, thái múi cau')" + "," +
                "(48, 'soup_10', 'Dầu ăn: để phi hành')" + "," +
                "(49, 'soup_10', 'Gia vị: muối, hạt nêm, bột ngọt, tiêu');";

        databaseSQLite.QuerySQL(INSERT_INGREDIENTS_SOUP);

        /*============================================================================================================*/
        String INSERT_INGREDIENTS_FRY = "INSERT OR IGNORE INTO Ingredients VALUES " +
                "(1, 'fry_1', 'Trứng gà: 4 quả')," +
                "(2, 'fry_1', 'Thịt heo bằm (hoặc thịt bò/xúc xích): 100–150 g')," +
                "(3, 'fry_1', 'Hành tím: 1 củ, băm nhỏ')," +
                "(4, 'fry_1', 'Hành lá: vài nhánh, thái nhỏ')," +
                "(5, 'fry_1', 'Gia vị: muối (~½ muỗng cà phê), tiêu xay (~¼ muỗng), hạt nêm, dầu ăn (2 muỗng canh)')," +

                "(1, 'fry_2', 'Cơm nguội: 3 chén (~450g) – tốt nhất là cơm trắng để nguội')," +
                "(2, 'fry_2', 'Lạp xưởng hoặc xúc xích: 2 – 3 cây, thái lát')," +
                "(3, 'fry_2', 'Tôm/ thịt băm (tùy chọn): 150g')," +
                "(4, 'fry_2', 'Trứng: 1–2 quả')," +
                "(5, 'fry_2', 'Hành tím + tỏi: mỗi loại 2 tép, băm nhỏ')," +
                "(6, 'fry_2', 'Rau củ: đậu hà lan, cà rốt, hành lá – tổng khoảng 100–150g')," +
                "(7, 'fry_2', 'Gia vị: dầu ăn, bơ (½ muỗng canh), dầu mè (tuỳ chọn), nước mắm, xì dầu, tiêu, đường hoặc đường phèn, bột nêm')," +

                "(1, 'fry_3', 'Thịt heo nạc vai xay: 400–600 g')," +
                "(2, 'fry_3', 'Giá đỗ: 200 g')," +
                "(3, 'fry_3', 'Cà rốt: 1 củ nhỏ (≈100 g), thái sợi')," +
                "(4, 'fry_3', 'Su hào hoặc củ đậu: ½ củ (≈150 g), thái sợi')," +
                "(5, 'fry_3', 'Hành tây: 1 củ nhỏ, thái sợi')," +
                "(6, 'fry_3', 'Hành lá: 1 nắm, thái nhỏ')," +
                "(7, 'fry_3', 'Mộc nhĩ + nấm hương: 20–30 g, ngâm nở, băm nhỏ')," +
                "(8, 'fry_3', 'Miến dong: 50 g, ngâm mềm, cắt khúc')," +
                "(9, 'fry_3', 'Trứng vịt/gà: 2 quả (½ trộn nhân, ½ dùng để dính vỏ)')," +
                "(10, 'fry_3', 'Bánh đa nem: 20–30 cái')," +
                "(11, 'fry_3', 'Dầu ăn để chiên')," +
                "(12, 'fry_3', 'Gia vị: muối, mắm, hạt nêm, tiêu, mì chính, giấm/đường (phết vỏ)')," +

                "(1, 'fry_4', 'Trứng gà: 3 quả')," +
                "(2, 'fry_4', 'Thịt heo bằm: 100–150 g (nạc vai hoặc nửa nạc nửa mỡ)')," +
                "(3, 'fry_4', 'Hành lá: 2–3 nhánh, thái nhỏ')," +
                "(4, 'fry_4', 'Hành tím: 1 củ nhỏ, băm nhuyễn')," +
                "(5, 'fry_4', 'Gia vị: 1 tsp nước mắm; ½ tsp bột canh/ hạt nêm; ½ tsp bột ngọt; ¼ tsp tiêu xay')," +
                "(6, 'fry_4', 'Dầu ăn: 1–2 muỗng canh')," +

                "(1, 'fry_5', 'Trứng gà: 3–4 quả')," +
                "(2, 'fry_5', 'Cà chua: 200 g (1–2 quả), cắt hạt lựu hoặc múi cau')," +
                "(3, 'fry_5', 'Hành lá: vài nhánh, thái nhỏ')," +
                "(4, 'fry_5', 'Hành tím: 1 củ, băm nhuyễn')," +
                "(5, 'fry_5', 'Gia vị: dầu ăn, muối, hạt nêm, bột ngọt (tuỳ chọn), tiêu; Dầu điều hoặc dầu mè (tuỳ chọn để tạo màu và hương thơm)')," +

                "(1, 'fry_6', 'Cánh gà: 600 g (~10–12 cánh), rửa sạch, chặt khúc (nếu to)')," +
                "(2, 'fry_6', 'Tỏi băm: 4 tép; Ớt (tuỳ chọn): 1–2 quả, băm nhỏ')," +
                "(3, 'fry_6', 'Nước mắm: 3 tbsp; Đường: 2 tbsp; Dầu hào: 1 tbsp; Tương ớt: 1 tbsp; Bột bắp: 1 tbsp (giúp lớp vỏ giòn)')," +
                "(4, 'fry_6', 'Gia vị: Muối, tiêu, hạt nêm, dầu ăn')," +
                "(5, 'fry_6', 'Gia vị khử mùi: gừng/giấm/muối (tuỳ chọn)')," +

                "(1, 'fry_7', '3–4 củ khoai tây (tốt nhất dùng khoai tây Ý hoặc Russet)')," +
                "(2, 'fry_7', '½ muỗng cà phê muối')," +
                "(3, 'fry_7', 'Dầu ăn (đủ chiên ngập hoặc chiên nông)')," +
                "(4, 'fry_7', 'Tùy chọn: tiêu, phô mai bột, bơ, lá oregano, tương cà, tương ớt')," +

                "(1, 'fry_8', 'Cá tươi sạch (cá rô, diêu hồng, basa, cá trích…) – khoảng 500 g')," +
                "(2, 'fry_8', 'Muối, tiêu, bột chiên giòn hoặc bột năng')," +
                "(3, 'fry_8', 'Dầu ăn để chiên ngập')," +
                "(4, 'fry_8', 'Nếu làm sốt: hành tím, tỏi, ớt, nước mắm, đường, chanh/tương ớt theo sở thích')," +

                "(1, 'fry_9', 'Thịt heo xay (nạc vai, nửa nạc nửa mỡ): 600 g')," +
                "(2, 'fry_9', 'Hành tím, tỏi băm: 2 củ + 4 tép')," +
                "(3, 'fry_9', 'Hành lá: vài nhánh, thái nhỏ')," +
                "(4, 'fry_9', 'Bột chiên/ bột bắp: 2 tbsp (giúp săn vỏ)')," +
                "(5, 'fry_9', 'Trứng: 1 quả (khi chiên)')," +
                "(6, 'fry_9', 'Gia vị: muối, hạt nêm, tiêu, đường, nước mắm')," +
                "(7, 'fry_9', 'Dầu ăn: đủ chiên ngập');";

        databaseSQLite.QuerySQL(INSERT_INGREDIENTS_FRY);

        /*============================================================================================================*/
        String INSERT_INGREDIENTS_DESSERT = "INSERT OR IGNORE INTO Ingredients VALUES " +
                "(1, 'dessert_1', 'Gạo nếp cái hoa vàng: 2 kg')," +
                "(2, 'dessert_1', 'Đậu xanh (bóc vỏ): 600 g')," +
                "(3, 'dessert_1', 'Thịt ba chỉ (nạc + mỡ): 1 kg')," +
                "(4, 'dessert_1', 'Lá dong: ~30 lá, rửa sạch, cắt bỏ sống lá')," +
                "(5, 'dessert_1', 'Lạt buộc (lạt giang hoặc dây nilon chịu nhiệt)')," +
                "(6, 'dessert_1', 'Gia vị: muối, tiêu, đường hoặc hạt nêm, nước mắm (nếu thích)')," +

                "(1, 'dessert_2', 'Gạo nếp: 2 kg, ngâm 6–12 giờ')," +
                "(2, 'dessert_2', 'Đậu xanh bóc vỏ: 0,6–0,7 kg, ngâm 3–4 giờ')," +
                "(3, 'dessert_2', 'Thịt ba chỉ: 1 kg, cắt miếng')," +
                "(4, 'dessert_2', 'Lá dong hoặc lá chuối: 30–60 lá, rửa sạch')," +
                "(5, 'dessert_2', 'Lạt buộc (lạt giang hoặc dây nilon chịu nhiệt)')," +
                "(6, 'dessert_2', 'Gia vị: muối, tiêu, đường, nước mắm (tuỳ chọn)')," +

                "(1, 'dessert_3', 'Chuối tây chín: 10 quả')," +
                "(2, 'dessert_3', 'Gạo nếp: 500 g, ngâm 6 tiếng')," +
                "(3, 'dessert_3', 'Đậu xanh: 200 g, bóc vỏ, hấp chín')," +
                "(4, 'dessert_3', 'Nước cốt dừa: 200 ml')," +
                "(5, 'dessert_3', 'Đường, muối, vani, mè rang')," +
                "(6, 'dessert_3', 'Lá chuối để gói: rửa sạch, trụng qua nước sôi')," +

                "(1, 'dessert_4', 'Bột năng: 200 g')," +
                "(2, 'dessert_4', 'Khoai lang tím: 1 củ nhỏ')," +
                "(3, 'dessert_4', 'Khoai lang vàng: 1 củ nhỏ')," +
                "(4, 'dessert_4', 'Nước cốt dừa: 200 ml')," +
                "(5, 'dessert_4', 'Đường, muối, mè rang, lá dứa (tuỳ chọn)')," +

                "(1, 'dessert_5', 'Đậu xanh cà vỏ: 200 g')," +
                "(2, 'dessert_5', 'Bột nếp: 300 g')," +
                "(3, 'dessert_5', 'Đường: 150–200 g')," +
                "(4, 'dessert_5', 'Gừng: 1 củ nhỏ')," +
                "(5, 'dessert_5', 'Nước cốt dừa: 200 ml')," +
                "(6, 'dessert_5', 'Muối, mè rang')," +

                "(1, 'dessert_6', 'Bột nếp: 300 g')," +
                "(2, 'dessert_6', 'Bột gạo: 50 g')," +
                "(3, 'dessert_6', 'Nước sôi: ~200 ml (để nhồi bột)')," +
                "(4, 'dessert_6', 'Đậu xanh hấp chín: 150 g')," +
                "(5, 'dessert_6', 'Đường, mè rang, gừng, nước cốt dừa')," +

                "(1, 'dessert_7', 'Trân châu khô: 100 g')," +
                "(2, 'dessert_7', 'Nước cốt dừa: 200 ml')," +
                "(3, 'dessert_7', 'Sữa đặc hoặc đường: tuỳ chọn')," +
                "(4, 'dessert_7', 'Lá dứa: vài lá để tạo mùi thơm')," +

                "(1, 'dessert_8', 'Khoai lang: 2 củ')," +
                "(2, 'dessert_8', 'Đường: 100–150 g')," +
                "(3, 'dessert_8', 'Gừng tươi: vài lát')," +
                "(4, 'dessert_8', 'Nước cốt dừa: 100 ml')," +
                "(5, 'dessert_8', 'Bột năng: 1–2 muỗng hoà tan tạo độ sánh')," +

                "(1, 'dessert_9', 'Bột rau câu giòn: 10 g')," +
                "(2, 'dessert_9', 'Đường: 200 g')," +
                "(3, 'dessert_9', 'Nước cốt lá dứa, nước cốt dừa, cà phê, ca cao, sữa… (để tạo lớp)')," +
                "(4, 'dessert_9', 'Nước lọc: 1 lít')," +

                "(1, 'dessert_10', 'Đậu đỏ: 200 g')," +
                "(2, 'dessert_10', 'Đường: 100–150 g')," +
                "(3, 'dessert_10', 'Muối: ½ tsp')," +
                "(4, 'dessert_10', 'Nước cốt dừa: 200 ml')," +
                "(5, 'dessert_10', 'Bột năng: 1 muỗng hoà tan với nước')," +

                "(1, 'dessert_11', 'Sữa tươi không đường: 1 lít')," +
                "(2, 'dessert_11', 'Đường: 100 g (tùy vị ngọt mong muốn)')," +
                "(3, 'dessert_11', 'Men cái sữa chua: 1–2 hộp hoặc 1 gói men')," +
                "(4, 'dessert_11', 'Hũ/chai/hộp đựng, nồi ủ hoặc thùng giữ nhiệt')";
        databaseSQLite.QuerySQL(INSERT_INGREDIENTS_DESSERT);

        /*============================================================================================================*/
        String INSERT_INGREDIENTS_FOREIGN = "INSERT OR IGNORE INTO Ingredients VALUES " +
                "(1, 'foreign_1', 'Mì Ý (spaghetti hoặc penne…): 400 g')," +
                "(2, 'foreign_1', 'Tỏi: 4–6 tép, băm nhỏ')," +
                "(3, 'foreign_1', 'Dầu ô liu: 4 tbsp')," +
                "(4, 'foreign_1', 'Gia vị: muối, tiêu, ớt khô tùy chọn')," +
                "(5, 'foreign_1', 'Rau thơm: ngò Ý (parsley) hoặc lá tía tô')," +
                "(6, 'foreign_1', 'Phô mai Parmesan bào (tuỳ chọn)')," +
                "(7, 'foreign_1', 'Nghêu → spaghetti alle vongole. Sốt thịt bằm (Bolognese). Sốt cà chua đơn giản (pomodoro).')," +

                "(1, 'foreign_2', 'Phần đế bánh (dough): 250 g bột mì (hoặc mix ‑ 00 flour cho pizza kiểu Ý); 160 ml nước ấm; 1/2 tsp muối + 1/2 tsp đường; 3 g men khô (instant yeast); 1 tbsp dầu ô liu.')," +
                "(2, 'foreign_2', 'Sốt & nhân: 200 g cà chua nghiền (hoặc sốt cà chua); 1 tsp dầu ô liu, 1–2 tép tỏi băm, chút muối, đường, oregano; 150–200 g phô mai mozzarella bào; Topping tùy chọn: pepperoni, xúc xích Ý, rau củ (ớt chuông, nấm, hành,...).')," +

                "(1, 'foreign_3', 'Thịt bò xay (80/20): 500 g')," +
                "(2, 'foreign_3', 'Trứng & breadcrumbs (tùy chọn): 1 trứng + ½ cup breadcrumbs nếu muốn giữ kết dính')," +
                "(3, 'foreign_3', 'Bánh mì burger: 4 cái')," +
                "(4, 'foreign_3', 'Phô mai lát (cheddar/American): 4 lát')," +
                "(5, 'foreign_3', 'Gia vị: muối, tiêu; thêm Worcestershire sauce (1 tbsp) / bột tỏi, hành bột theo sở thích')," +
                "(6, 'foreign_3', 'Rau & topping: xà lách, cà chua/thịt/pickle/hành/tương/mayo/BBQ… tùy chọn')," +

                "(1, 'foreign_4', 'Bột: 100 g bột mì đa dụng hoặc bột bánh (cake flour), 1 quả trứng, 300–500 ml nước dùng dashi, 1 tsp baking powder, muối, xì dầu hoặc tsuyu')," +
                "(2, 'foreign_4', 'Nhân: 50–120 g bạch tuộc luộc thái nhỏ; thêm tương tự: tôm, xúc xích, phô mai, đậu phụ... nếu muốn biến tấu')," +
                "(3, 'foreign_4', 'Topping: beni shoga (gừng đỏ), tenkasu, hành lá, takoyaki sauce, mayonnaise Nhật, aonori (rong biển khô), katsuobushi (bọt cá ngừ khô)')," +

                "(1, 'foreign_5', 'Cơm sushi: Gạo Nhật: 2 cup; Nước: 2.2 cup; Giấm sushi: 4 tbsp (gồm giấm gạo + đường + muối).')," +
                "(2, 'foreign_5', 'Nhân cuộn: Cá hồi sống (sashimi-grade), thanh cua, tôm luộc, trứng cuộn (tamago); Dưa leo, bơ, củ cải muối (takuan), rau cải.')," +
                "(3, 'foreign_5', 'Khác: Rong biển khô (nori); Mành tre cuốn sushi; Xì dầu, wasabi, gừng ngâm.')," +

                "(1, 'foreign_6', 'Cá sashimi-grade: cá hồi, cá ngừ, hamachi… mỗi loại 150–200 g')," +
                "(2, 'foreign_6', 'Phụ kiện: wasabi, gừng ngâm (gari), nước tương Nhật')," +
                "(3, 'foreign_6', 'Trang trí: củ cải trắng (daikon bào sợi), lá shiso, rong biển, hoa dương')," +
                "(4, 'foreign_6', 'Dụng cụ: dao sashimi (đầu dài – yanagiba), thớt sạch')," +

                "(1, 'foreign_7', 'Trứng: 2–3 quả')," +
                "(2, 'foreign_7', 'Bơ hoặc dầu: 1–2 tsp; Phô mai: 2–3 tbsp (mozzarella, cheddar…).')," +
                "(3, 'foreign_7', 'Muối, tiêu: 1 ít')," +
                "(4, 'foreign_7', 'Tùy chọn nhân: rau chân vịt (spinach), hành lá, nấm, jambon, dăm bông…')," +

                "(1, 'foreign_8', 'Cơm nóng: gạo dẻo như Japonica, nếp lẫn ít tẻ (~2–3 bát cơm)')," +
                "(2, 'foreign_8', 'Nhân (tuỳ chọn): cá ngừ mayonnaise, ruốc thịt/chà bông, cá hồi, mứt, đậu, rong biển…')," +
                "(3, 'foreign_8', 'Rong biển nori: nếu làm phiên bản Nhật onigiri')," +
                "(4, 'foreign_8', 'Gia vị: muối, dầu mè/giấm nhẹ (nếu muốn), muối vừng hoặc mè rang (muối vừng)')," +

                "(1, 'foreign_9', 'Thịt bò xay (80/20): 500 g')," +
                "(2, 'foreign_9', 'Tortilla (bắp/mì): 8–10 chiếc (khoảng 15 cm)')," +
                "(3, 'foreign_9', 'Gia vị thịt: dầu ô liu, 1 củ hành tây băm, 2 tép tỏi, 1 tsp ớt bột, 1 tsp cumin, ½ tsp paprika, muối & tiêu')," +
                "(4, 'foreign_9', 'Nước sốt: ½ cup cà chua nghiền hoặc salsa')," +
                "(5, 'foreign_9', 'Toppings: xà lách, cà chua thái hạt lựu, hành tây, cilantro, pho mát (cheddar/Monterey Jack), guacamole, sour cream')," +

                "(1, 'foreign_10', 'Gà nguyên con: 1–1.5 kg, làm sạch')," +
                "(2, 'foreign_10', 'Gia vị ướp: sả, tỏi, hành tím, gừng, ngũ vị, tiêu, muối, dầu ăn')," +
                "(3, 'foreign_10', 'Bọc: lá chuối – giấy bạc – lớp đất sét')," +
                "(4, 'foreign_10', 'Dụng cụ: lò nướng, than hoa hoặc bếp than + vỉ, khăn sạch');";
        databaseSQLite.QuerySQL(INSERT_INGREDIENTS_FOREIGN);
    }

    /*Thêm dữ liệu vào Recipes*/
    public static void InsertData_Recipes(Context context) {
        DatabaseSQLite databaseSQLite = new DatabaseSQLite(context, "APP_TEACHING_COOK.sqlite", null, 1);

        String INSERT_RECIPES_STIR_FRY = "INSERT OR IGNORE INTO Recipes VALUES " +
                "(1, 'stir_fry_1', 'Rau muống mua về nhặt thành khúc khoảng 10cm, bỏ lá hư và cọng già, rửa sạch rồi để ráo. Tỏi đập dập rồi băm nhuyễn.')," +
                "(2, 'stir_fry_1', 'Chần sơ rau muống qua nước sôi với 1 chút muối rồi nhanh tay cho vào chậu nước đá lạnh. Cách làm này sẽ giúp rau muống giòn hơn và giữ được màu xanh mướt.')," +
                "(3, 'stir_fry_1', 'Cho 3 - 4 muỗng dầu ăn vào chảo, đến khi dầu nóng thì cho tỏi vào phi thơm. Tiếp đến cho rau vào đảo đều, nêm nếm nước mắm, hạt nêm và đường sao cho vừa ăn rồi tắt bếp. Cho rau muống ra đĩa và rắc thêm tiêu để món ăn thêm đậm đà. Rau muống giòn dai, đẫm vị, tỏi thơm thơm, cay cay vị đặc trưng chắc chắn sẽ là một món cơm nhà khó quên.')," +

                "(1, 'stir_fry_2', 'Hến mua về bạn rửa sạch với nước nhiều lần rồi để ráo. Giá bạn lấy sạch vỏ đậu rồi rửa sạch để ráo. Hẹ cũng mang đi rửa sạch rồi bạn cắt khúc dài cỡ 2 lóng tay. Hành tím bạn lấy 2 củ, tỏi lấy 2 tép rồi đi băm nhỏ.')," +
                "(2, 'stir_fry_2', 'Bắc chảo lên bếp, bạn cho vào 2 muỗng canh dầu ăn, dầu nóng thì cho hết phần hành tím và tỏi băm vào phi thơm rồi cho hến vào. Bạn nêm vào chảo 1/3 muỗng canh muối, 1 muỗng canh hạt nêm và 1/2 muỗng canh đường, đảo đều cho hến thấm gia vị, xào khoảng 2 - 3 phút.')," +
                "(3, 'stir_fry_2', 'Tiếp theo bạn cho giá với hẹ vào xào khoảng 3 - 4 phút cho giá vừa chín tới là được nhé, bạn nêm nếm lại cho vừa ăn rồi tắt bếp. Hến xào giá hẹ có vẻ ngoài đẹp mắt của thịt hến trắng và rau hẹ xanh mát mắt. Món ăn hấp dẫn bởi thịt hến ngon ngọt kết hợp với giá giòn thơm, rau hẹ hăng hăng, gia vị đậm đà.')," +

                "(1, 'stir_fry_3', 'Thịt ếch làm sạch, chặt khúc, rửa lại với nước, để ráo. Cà tím cắt khúc khoảng 2-3cm. Gừng gọt vỏ, cắt sợi, sả cắt nhỏ.')," +
                "(2, 'stir_fry_3', 'Phi thơm sả, gừng với 2 muỗng canh dầu ăn. Cho thịt ếch vào, xào chín. Nêm gia vị 1 muỗng canh sa tế, 1 muỗng canh nước mắm, 1/2 muỗng cà phê tiêu, 1 muỗng cà phê hạt nêm, 1/2 muỗng cà phê bột ngọt, đảo đều.')," +
                "(3, 'stir_fry_3', 'Cuối cùng, cho cà tím, ngò gai cắt nhỏ vào, xào chín là được. Bày món ăn ra đĩa và ăn cùng với cơm nóng. Thêm thực đơn bổ dưỡng này vào bữa ăn tối nay nhé')," +

                "(1, 'stir_fry_4', 'Hành, tỏi băm nhuyễn để ướp và phi thơm. Thịt bò rửa sạch, cắt miếng nhỏ vừa ăn. Cải thìa cắt bỏ phần đuôi, đối với những cây to thì cắt làm bốn, cây nào dài thì cắt đôi để vừa ăn, sau đó mang đi rửa sạch.')," +
                "(2, 'stir_fry_4', 'Cho vào bát lần lượt 1 muỗng canh hạt nêm, 1/2 muỗng canh đường, 2 muỗng canh dầu,1/2 phần hành, tỏi đã băm nhuyễn ban đầu, nếu thích ăn mì hơi dai dai một chút thì cho thêm 1 muỗng canh bột bắp vào sau đó trộn đều lên ướp trong 5 phút.')," +
                "(3, 'stir_fry_4', 'Nấu nước sôi hoặc có thể dùng bình đun siêu tốc để tiết kiệm thời gian. Lần lượt cho từng gói mì vào nồi. Mì trụng mềm vừa tới, không để quá rục vì sau đó sẽ còn bước xào mì, nếu mì quá mềm thì mì sẽ bị nát.')," +
                "(4, 'stir_fry_4', 'Đặt chảo lên bếp, đợi chảo nóng rồi cho dầu ăn vào tráng đều chảo, phi thơm hết phần hành, tỏi còn lại. Cho toàn bộ phần cải thìa vào xào, nêm thêm 1 muỗng hạt nêm và đảo đều tay đến khi chín tới.')," +

                "(1, 'stir_fry_5', 'Sườn sau khi mua về, bạn rửa sạch với nước và muối rồi đem sườn đi trụng sơ với nước sôi cho ra hết chất bẩn. Sườn sau khi trụng qua, bạn ướp với 1 muỗng hạt nêm và nửa muỗng tiêu trong khoảng 10 - 15 phút cho thấm gia vị. Cà chua bạn rửa sạch rồi cắt hạt lựu, hành lá cắt nhuyễn để tăng thêm hương vị cho món ăn.')," +
                "(2, 'stir_fry_5', 'Làm nóng chảo cùng 2 muỗng dầu ăn, khi dầu nóng thì bạn cho sườn vào chiên đến khi sườn chín. Khi sườn sắp chín, bạn nhớ cho phần tỏi băm đã chuẩn bị vào phi thơm chung để tăng thêm mùi hương cho sườn nhé.')," +
                "(3, 'stir_fry_5', 'Vớt sườn ra để riêng cho ráo dầu. Sau đó giữ nguyên chảo dầu chiên sườn lúc trước, làm nóng và cho cà chua vào xào đến khi nhuyễn. Nêm nếm thêm gia vị sao cho chua ngọt vừa ăn, nếu thích ăn cay có thể cho thêm tương ớt để tăng màu sắc cho món ăn và thêm vị cay cay hấp dẫn nhé.')," +
                "(4, 'stir_fry_5', 'Khi sốt cà đã hơi sệt, cho sườn chiên vào và đảo đều cho đến khi sốt thấm vào thịt và bám dính lên từng miếng sườn. Rắc hành lá lên rồi tắt bếp, vậy là món ăn của chúng ta đã hoàn thành xong.')," +

                "(1, 'stir_fry_6', 'Bí xanh gọt vỏ,bỏ ruột,cắt sợi dài hay ngắn tùy thích,lạc rang chín,làm sạch vỏ lụa, chà sơ không quá nhỏ.')," +
                "(2, 'stir_fry_6', 'Bắc chảo nóng,cho dầu vào,phi thơm tỏi, cho bí vào xào,thêm tý nước sạch,xào trên lửa lớn tầm 3p,cho tý mắm tôm vào,tiếp cho bột canh xào thêm 1p, cho gia vị tiêu,bột ngọt,ớt,đậu phộng vào xào thêm 1p nữa là tắt bếp. Cuối cùng, cho ra đĩa dùng như món rau đặc biệt.mời cả nhà cùng thưởng thứ')," +

                "(1, 'stir_fry_7', 'Thịt lươn làm sẵn sau khi mua về thì rửa lại với nước rồi cắt miếng vừa ăn.')," +
                "(2, 'stir_fry_7', 'Rau răm bạn rửa sạch, bỏ cọng và lá sâu rồi cắt nhỏ. Ớt sừng và ớt hiểm rửa sạch, bỏ cuống, ớt sừng cắt lát chéo, ớt hiểm cắt nhỏ. Hành khô và hành tăm bóc vỏ rồi đập dập và băm nhỏ. Đối với sả, bóc bỏ lớp bẹ già, cắt gốc, rửa sạch sau đó lấy ⅔ số sả đi băm nhuyễn, số còn lại thì cắt lát chéo.')," +
                "(3, 'stir_fry_7', 'Cho vào phần lươn đã chuẩn bị 1 muỗng cà phê nước mắm, 1 muỗng cà phê tiêu xay, một ít hành khô, hành tăm và sả băm rồi trộn đều. Ướp lươn khoảng 15 - 20 phút để lươn thấm đều gia vị.')," +
                "(4, 'stir_fry_7', 'Bắc chảo lên bếp và cho vào 2 muỗng canh dầu ăn và 1 muỗng canh dầu màu điều. Khi dầu nóng thì bạn cho số hành khô, hành tăm còn lại cùng sả cắt lát vào phi cho vàng thơm. Sau đó, bạn cho thịt lươn vào xào đều tay trên lửa vừa. Khi lươn chín và săn lại, nêm thêm 1 muỗng cà phê nước mắm, 1 muỗng cà phê bột ngọt, 1/2 muỗng cà phê ớt bột. Đảo đều lươn cho gia vị tan hết rồi cho rau răm và ớt sừng vào xào chung khoảng 20 giây thì tắt bếp.')," +
                "(5, 'stir_fry_7', 'Cho lươn xào ra đĩa, rắc thêm một ít tiêu là có ngay món lươn xào sả ớt vô cùng hấp dẫn. Thịt lươn mềm ngọt, đậm vị sả ớt và thoang thoảng mùi rau răm mà ăn với cơm nóng thì ngon không gì bằng.')," +

                "(1, 'stir_fry_8', 'Rửa mực thật sạch, loại bỏ phần nội tạng và lớp màng bên ngoài, sau đó cắt thành miếng vừa ăn. Ngâm nấm đông cô và nấm hương trong nước khoảng 15 phút để chúng nở mềm, rồi vớt ra, rửa lại bằng nước sạch. Nấm đùi gà ngâm trong nước muối loãng khoảng 7 phút, sau đó rửa sạch và cắt lát vừa ăn. Tỏi bóc vỏ và băm nhỏ, ớt và hành lá rửa sạch, cắt nhỏ để sẵn.')," +
                "(2, 'stir_fry_8', 'Cho vào chén 1 muỗng cà phê bột ngọt, 1 muỗng cà phê hạt nêm, 3 muỗng cà phê đường cát, 1/2 muỗng cà phê tiêu xay, 1/2 muỗng cà phê ớt khô, 4 muỗng cà phê nước tương, 2 muỗng cà phê dầu hào, 2 muỗng cà phê tương ớt, 2 muỗng canh nước lọc và khuấy đều để làm nước sốt ướp.')," +
                "(3, 'stir_fry_8', 'Cho 1/2 chén nước sốt ướp vào phần mực đã sơ chế và ướp trong vòng khoảng 10 phút cho mực thấm đều gia vị.')," +
                "(4, 'stir_fry_8', 'Bắc chảo lên bếp, cho một ít dầu ăn vào và đun nóng. Khi dầu nóng, cho tỏi băm vào phi thơm, tiếp đó cho mực vào xào nhanh tay trên lửa lớn. Khi mực săn lại thì  vớt ra để riêng. Tiếp tục sử dụng chảo đó, cho nấm đông cô, nấm hương và nấm đùi gà vào xào. Khi nấm bắt đầu chín và mềm, đổ phần nước sốt đã pha vào chảo, đảo đều để nấm thấm gia vị. Cuối cùng, cho mực đã xào vào trở lại, thêm ớt và hành lá, đảo đều khoảng 3 - 5 phút nữa là món ăn hoàn thành.')," +

                "(1, 'stir_fry_9', 'Tôm rửa sạch để ráo nước, sau đó cho vào tô, ướp với 1 ít hạt nêm hạt nêm, 1 ít dầu ăn, xóc đều lên, để 5 phút cho ngấm.')," +
                "(2, 'stir_fry_9', 'Đặt chảo lên bếp, khi chảo nóng thì bạn cho vào 1 ít dầu ăn, khi dầu sủi tăm thì bạn cho tỏi vào để phi thơm. Sau đó cho tôm vào và đảo đều tay, vặn lửa lớn, xào khoảng 5 phút thì tôm chín thì tắt bếp. Để đến khi tôm nguội thì bạn bắt đầu lột vỏ.')," +
                "(3, 'stir_fry_9', 'Cho một cái chảo khác lên bếp với 1 ít dầu ăn, tiếp tục cho thêm một ít tỏi vào phi thơm. Tiếp đến, cho tôm đã bóc vỏ vào đảo đều, vặn nhỏ lửa, nêm nước mắm, đường, 1 xíu muối. Nếm xem độ mặn ngọt vừa như ý là được. Đảo đều, để tôm không bị cháy, tôm săn lại thì cho hành hoa, ớt vào đảo đều rồi tắt bếp. Sau khi tắt bếp thì bạn cho ra dĩa, thêm chút hành lá lên trên là có thể bày ra thưởng thức.')," +

                "(1, 'stir_fry_10', 'Thịt bò sau khi mua về rửa sơ, rồi ngâm với nước muối loãng khoảng 5 phút để loại bỏ bớt mùi hôi của thịt bò, sau đó rửa lại với nước sạch, dùng dao thái thịt thành từng miếng nhỏ. Súp lơ rửa sạch dưới vòi nước, rồi cắt thành từng khúc vừa ăn. Hành tây lột vỏ, rửa sạch, rồi cắt múi cau. Hành lá rửa sạch, dùng dao thái nhuyễn phần hành. Với tỏi, lột vỏ, đập dập rồi băm nhuyễn.')," +
                "(2, 'stir_fry_10', 'Cho thịt bò vào trong tô nhỏ với một ít dầu ăn, đường, tiêu, nước tương, trộn đều, rồi ướp phần thịt bò trong 15 phút để thịt thấm đều gia vị.')," +
                "(3, 'stir_fry_10', 'Bắt chảo lên bếp với một ít dầu ăn, khi dầu đã nóng lên thì cho tỏi vào, phi lên cho vàng thơm. Tiếp đó, cho súp lơ và hành tây vào xào trong 2 phút, sau đó cho tiếp phần thịt bò vào, đảo đều cho phần thịt săn lại. Khi phần thịt đã săn lại, cho vào một ít nước, nêm nếm với gia vị cho vừa ăn, xào thêm 5 phút đến khi các nguyên liệu chín thì tắt bếp. Cho thịt bò xào súp lơ ra đĩa, trang trí với một ít hành lá, rồi thưởng thức thôi.');";

        databaseSQLite.QuerySQL(INSERT_RECIPES_STIR_FRY);

        /*============================================================================================================*/
        String INSERT_RECIPES_BRAISE = "INSERT OR IGNORE INTO Recipes VALUES " +
                "(1, 'braise_1', 'Thịt heo sau khi mua về bạn cắt nhỏ thành khối vừa ăn (khoảng 4-5 cm) rồi rửa sạch với nước. Trứng vịt luộc chín sau đó bóc vỏ.  Hành tím và tỏi bóc vỏ, 1 nửa băm nhuyễn, 1 nửa dập dập. Ớt sứng bỏ cuỗng, băm nhuyễn .')," +
                "(2, 'braise_1', 'Bạn ướp 1kg thịt ba chỉ với hành tỏi ớt sừng băm,3 muỗng canh nước mắm, 1 muỗng canh hạt nêm, 1 muỗng cà phê nước cốt chanh, 1 muỗng canh bột ngọt, 3 muỗng canh nước màu đường, bạn trộn đều và ướp thịt trong vòng 1 tiếng.')," +
                "(3, 'braise_1', 'Cho thịt đã ướp vào áp chảo sơ rồi vớt ra cho vào 1 cái nồi. Tiếp đến bạn thêm 700ml nước dừa, 300ml nước lọc, hành tỏi đập dập vào. Ở 30 phút đầu, bạn đun với lửa to vừa và hớt bọt thường xuyên. Sau 30 phút bạn cho tiếp 300ml nước lọc vào và đun lửa nhỏ trong 1 tiếng. Cứ mỗi 5 phút, bạn đảo thịt 1 lần để thịt không bị cháy ở cạnh.')," +
                "(4, 'braise_1', 'Tiếp đó là bạn thêm trứng vịt vào, chỉnh lửa thật nhỏ và đun thêm 30 phút nữa. Cuối cùng bạn nêm vào thêm 3 muỗng canh nước mắm, 1 muỗng canh đường phèn vào, đợi đường tan rồi tắt bếp. Vậy là bạn đã nấu xong món thịt kho tàu kiểu miền Nam thơm ngon rồi. Công thức đơn giản quá phải không nào, lưu lại ngày để trổ tài nấu cho gia đình nhé!'), " +

                "(1, 'braise_2', 'Đầu tiên, bạn mang thịt ba chỉ rửa sạch với nước muối loãng, rửa lại lần nữa với nước sạch, rồi để ráo. Sau đó, bạn cắt thịt thành từng miếng vừa ăn, cho vào nồi.')," +
                "(2, 'braise_2', 'Kế đến, bạn đập dập 20g hành tím và 20g tỏi, đem cả hai băm nhuyễn và cho vào nồi thịt. Tiếp theo, bạn cắt đôi 20g hành lá, lấy phần đầu hành và cắt nhỏ, cũng cho vào nồi thịt. Bạn thêm vào 3 muỗng canh nước mắm, 2 muỗng canh đường, 1/2 muỗng cafe muối, 1/2 muỗng cafe bột ngọt, 1/2 muỗng cafe bột canh nấm bào ngư, 1 muỗng cafe nước màu dừa, 1 muỗng cafe tiêu, 2 quả ớt hiểm đập dập và trộn các nguyên liệu với nhau, để 30 phút cho thấm gia vị.')," +
                "(3, 'braise_2', 'Bắt nồi lên bếp, đậy nắp lại và kho cho thịt săn lại thì đổ nước ngập mặt thịt. Kế đến, bạn đậy nắp nồi lại và kho trong 15 phút ở mức lửa nhỏ. Sau 15 phút, nồi thịt đã sánh lại thì tắt bếp, dọn ra bàn và trang trí 1 ít tiêu xanh là hoàn thành.')," +

                "(1, 'braise_3', 'Cá mua về mổ rồi rửa sạch (bạn có thể dùng chanh hoặc giấm để khử mùi tanh của cá), sau đó đó bạn cắt cá thành khúc dày 3-4 cm. Thịt ba chỉ rửa sạch rồi cắt nhỏ. Tỏi bóc vỏ, đập dập rồi băm nhuyễn. Ớt đập dập, hành lá rửa sạch rồi cắt khúc 3-4 cm.')," +
                "(2, 'braise_3', 'Cho cá vào tô, ướp cùng tỏi, ớt, 1 muỗng nước mắm, 1/2 muỗng đường, một ít tiêu trong 20 phút cho cá thấm gia vị. Thịt ba chỉ cũng ướp với tỏi, nước mắm, đường, tiêu trong 20 phút cho thấm gia vị.')," +
                "(3, 'braise_3', 'Bạn nên dùng nồi đất để kho cá, vì nồi đất sẽ giúp cá ngon và thấm vị hơn. Đặt nồi lên bếp, cho vào 2 muỗng đường đun đến khi đường tan và chuyển sang màu nâu cánh gián thì cho thịt vào đảo đều. Khi thịt đã săn thì cho cá vào lật đi lật lại liên tục. Cho thêm nước mắm cho đậm đà. Rưới 1 thìa dầu ăn để cá không bị khô hay tanh. Đun lửa to và cho nước săm sắp, khi sôi lại thì vặn nhỏ lửa, đậy nắp nồi lại kho trong 30 phút đến khi nước trong nồi sánh lại. Khi nước trong nồi đã sánh lại, cho hành lá cắt khúc cùng một ít tiêu vào rồi tắt bếp.')," +

                "(1, 'braise_4', 'Đậu hũ đem rửa sơ để ra đĩa. Thịt đem rửa sạch sau đó băm nhuyễn hoặc nếu bạn có máy xay thịt thì có thể cho thịt vào máy xay. Nấm tai mèo đem ngâm với nước cho nở ra, sau khi nấm tai mèo đã nở thì bạn đem rửa sạch cắt bỏ gốc và băm nhỏ. Đem thịt đã xay nhuyễn cùng nấm tai mèo băm nhỏ trộn đều với nhau thêm vào chút bột nêm và bột ngọt cho vừa ăn.')," +
                "(2, 'braise_4', 'Để đậu hũ không bị nát khi bạn nhồi thịt vào thì bạn có thể chiên sơ đậu hũ trước. Hãy bắc chảo lên bếp cho nóng rồi đổ dầu ăn vào đun sôi, cắt đậu hũ thành từng miếng và bỏ vào chảo chiên vàng hai mặt. Lưu ý: chỉ chiên sơ đậu hũ không chiên vàng quá vì sau khi nhồi thịt vào bên trong miếng đậu bạn sẽ còn chiên thêm một lần nữa nếu như chiên quá vàng mà chiên lại đậu sẽ dễ bị cháy và ăn không ngon.')," +
                "(3, 'braise_4', 'Dùng kéo hoặc dao rạch miếng đậu hũ theo chiều dọc, sau đó dùng chiếc muỗng nhỏ khoét bỏ phần đậu hũ bên trong. Tiếp theo lấy phần thịt đã trộn trước đó nhồi vào trong miếng đậu.')," +
                "(4, 'braise_4', 'Dùng chiếc chảo đã chiên đậu trước đó, bắc lên bếp, bỏ thêm dầu ăn vào và cho đậu đã nhồi thịt vào chiên. Tuy nhiên để phần thịt bên trong chín và đậu hũ không bị cháy thì bạn cần vặn bếp nhỏ lửa. Chiên vàng một mặt sau đó lật mặt còn lại chiên tiếp đến khi miếng đậu hũ chín vàng đều cả hai mặt là được. Gắp đậu hũ nhồi thịt ra đĩa và dùng chung với cơm hoặc cũng có thể dùng làm món nhậu cho ông xã cũng rất tuyệt vời. Đặc biệt, bạn có thể chấm đậu hũ nhồi thịt với tương ớt hoặc tương cà để gia tăng hương vị.')," +

                "(1, 'braise_5', 'Thịt ba chỉ bạn rửa sạch để ráo sau đó cắt thành những miếng vuông nhỏ vừa ăn. Trứng cút bạn cũng mang đi rửa sạch rồi cho vào nồi và luộc khoảng 7 - 10 phút cho chín. Sau khi trứng chín thì chờ cho trứng nguội rồi bóc vỏ. Sau đó, bạn rửa trứng qua một lần nước sạch nữa để loại bỏ hết những vụn vỏ còn dính trên trứng. Ớt sừng và hành lá bạn rửa sạch rồi băm nhỏ để trang trí.')," +
                "(2, 'braise_5', 'Bạn ướp thịt ba chỉ với 1 gói gia vị thịt kho, thêm 1 muỗng cà phê bột ngọt, 1 muỗng cà phê muối, 1 muỗng cà phê nước mắm, 1 muỗng cà phê nước hàng, 1 ít tiêu xay, trộn đều và để khoảng 30 phút cho thịt ngấm gia vị. Trong khi chờ ướp, bạn bắc chảo lên bếp rồi đổ vào 1 muỗng canh dầu ăn. Dầu nóng thì bạn cho trứng cút vào và chiên lửa vừa cho trứng chín vàng đều. Sau đó, bạn vớt trứng ra rây để cho ráo mỡ. Đầu tiên, bạn đặt chảo lên bếp rồi cho phần thịt đã ướp vào chảo, xào khoảng 3 phút để thịt hơi săn lại rồi cho vào nồi kho.')," +
                "(3, 'braise_5', 'Tiếp theo bạn cho thêm 1 chén (chén ăn cơm) nước lọc vào nồi để kho thịt. Khi thịt sôi thì cho phần trứng cút đã chiên, 1 chút ớt băm vào nồi. Bạn kho trên lửa nhỏ khoảng 30 phút đến khi thịt mềm thì tắt bếp. Sau khi hoàn thành món thịt kho tàu trứng cút thơm ngon nóng hổi rồi. Khi ăn bạn sẽ cảm nhận được miếng thịt mềm béo ngậy, vỏ ngoài của trứng cút dai dai, bên trong mềm không bị nát và tanh. Món này ăn với cơm nóng là hợp nhất nhé.')," +

                "(1, 'braise_6', 'Thịt gà sau khi mua về thì rửa sạch, ngâm với nước muối loãng trong 5 phút để khử bớt mùi hôi, rửa sạch lại với nước một lần nữa, sau đó chặt thành từng khúc vừa ăn. Nghệ gọt bỏ lớp vỏ bên ngoài, rửa sạch rồi cắt thành từng lát nhỏ hoặc băm nhuyễn đều được. Với món ăn này nên chọn những củ nghệ già, để món ăn sau khi nấu ra sẽ có màu đẹp và thơm ngon hơn. Cho bột nghệ vào trong một cái chén, cho nước từ từ vào, vừa cho vừa khuấy đều cho bột nghệ tan hết. Với tỏi thì đem đi lột bỏ vỏ, rồi băm nhuyễn. Gừng cạo bỏ vỏ, rồi cắt nhuyễn.')," +
                "(2, 'braise_6', 'Cho thịt gà vào một cái tô lớn, sau đó thêm vào một ít dầu ăn, nước mắm, tiêu, muối, đường, gừng, nghệ tươi và phần bột nghệ đã pha vào, trộn đều rồi ướp thịt gà trong 30 phút cho thấm gia vị.')," +
                "(3, 'braise_6', 'Cho dầu ăn vào trong nồi, đun đến khi dầu nóng lên thì cho tỏi vào phi lên cho vàng thơm. Tiếp đó, cho hết phần thịt gà và nước ướp vào, đảo đều đến khi thịt gà săn lại, thì cho nước lọc vào xâm xấp mặt thịt. Tiếp tục kho đến khi nước trong nồi sệt lại, thịt gà chín mềm thì nêm nếm lại cho vừa khẩu vị rồi tắt bếp. Vậy là món gà kho nghệ đã xong rồi, cho thịt gà ra đĩa, rắc thêm một ít tiêu vào, trang trí theo sở thích rồi thưởng thức thôi nào.')," +

                "(1, 'braise_7', 'Lựa cá nục con nhỏ vừa, làm sạch bụng, cắt bỏ vây cá, rửa sạch những đường gân máu gần xương cá. Cá sẽ để nguyên con không cắt khúc. Sau đó mang cá đi ngâm với nước trà pha loãng khoảng 10 phút để khử mùi tanh của cá.')," +
                "(2, 'braise_7', 'Cà chua sau khi mua về thì bạn rửa sạch. Sau đó bỏ cuống, cắt làm đôi rồi mang đi cắt hạt lựu.')," +
                "(3, 'braise_7', 'Bạn cho dầu vào chảo, dầu nóng cho vào tỏi, ớt, hành tím vào phi vàng. Kế đến thêm 3 muỗng canh nước mắm, 2 muỗng cafe bột nêm, 1 muỗng cafe bột ngọt 1/2 muỗng cafe muối 1 muỗng canh tương ớt, 2 muỗng canh cà hộp, đảo đều rồi đun trên lửa vừa đến khi hỗn hợp hòa quyện.')," +
                "(4, 'braise_7', 'Cắt mía thành lát lót dưới cùng của nồi rồi sắp cá lên trên cùng, các bạn nhớ sắp đều cá để cá chín đồng đều, sau đó cho phần sốt cà chua vào nồi. Cho thêm 500ml nước dừa tươi vào nồi. Sau đó thêm hành, tỏi ớt vào. Đậy chặt nắp, kho cá với lửa nhỏ vừa trong khoảng 2 tiếng. Chỉ với cách làm đơn giản là bạn đã hoàn thành món cá nục kho cà chua rồi. Món ăn trông vô cùng hấp dẫn khiến ai cũng muốn thử ngay thôi. Còn chờ gì nữa mà không vào bếp trổ tài cho cả nhà nào!')," +

                "(1, 'braise_8', 'Sơ chế sườn non: Sườn non rửa sạch với nước muối loãng, trụng qua nước sôi để khử mùi hôi, sau đó chặt miếng vừa ăn và để ráo.')," +
                "(2, 'braise_8', 'Ướp sườn: Ướp sườn với hành tím, tỏi băm, nước mắm, đường, hạt nêm và tiêu xay trong khoảng 20–30 phút cho thấm đều gia vị.')," +
                "(3, 'braise_8', 'Làm nước màu: Cho đường và chút dầu ăn vào chảo, đun lửa nhỏ đến khi chuyển màu cánh gián, sau đó cho sườn vào đảo đều đến khi săn lại.')," +
                "(4, 'braise_8', 'Kho sườn: Thêm nước dừa (hoặc nước lọc) xâm xấp mặt sườn, đun sôi rồi hạ nhỏ lửa, kho liu riu khoảng 30–40 phút cho sườn mềm và nước sệt lại.')," +
                "(5, 'braise_8', 'Nêm nếm và hoàn thiện: Nêm lại gia vị vừa ăn, rắc thêm tiêu xay và hành lá cắt nhỏ nếu thích. Tắt bếp và dọn ra dùng nóng với cơm trắng.')," +

                "(1, 'braise_9', 'Sơ chế vịt: Chặt vịt thành miếng vừa ăn, rửa sạch với rượu trắng hoặc gừng giã nhỏ để khử mùi tanh. Rửa lại bằng nước sạch, để ráo')," +
                "(2, 'braise_9', 'Ướp thịt vịt: Ướp vịt với hành tím, tỏi, sả băm nhuyễn, ớt thái lát, 1 muỗng nước mắm, 1 muỗng đường, 1 muỗng hạt nêm, 1 muỗng nước màu. Trộn đều và ướp trong 30 phút.')," +
                "(3, 'braise_9', 'Phi thơm sả ớt: Đun nóng dầu, phi thơm phần sả, tỏi, ớt còn lại để dậy mùi thơm, sau đó cho thịt vịt vào xào săn')," +
                "(4, 'braise_9', 'Kho thịt vịt: Thêm một chút nước (xâm xấp mặt thịt), đun sôi rồi hạ nhỏ lửa, kho liu riu khoảng 40–50 phút đến khi vịt mềm và nước kho sệt lại.')," +
                "(5, 'braise_9', 'Nêm nếm và hoàn thành: Nêm nếm lại gia vị vừa ăn, rắc tiêu và ớt nếu muốn cay hơn. Tắt bếp, dọn ra ăn kèm cơm nóng.')," +

                "(1, 'braise_10', 'Sơ chế ba chỉ: Thịt ba chỉ rửa sạch, cắt miếng vừa ăn. Ướp với 1 muỗng nước mắm, chút hạt nêm và tiêu trong 20 phút.')," +
                "(2, 'braise_10', 'Phi thơm tỏi, hành: Băm nhỏ tỏi và hành tím, cho vào chảo dầu phi thơm vàng.')," +
                "(3, 'braise_10', 'Thắng nước màu: (Nếu chưa có sẵn): Cho 1 muỗng đường vào chảo, đun đến khi chuyển màu cánh gián thì cho chút nước vào.')," +
                "(4, 'braise_10', 'Kho thịt: Cho thịt vào chảo đảo săn, rồi cho nước mắm, nước màu và ít nước lọc ngập mặt thịt. Đun sôi rồi kho lửa nhỏ 30–40 phút.')," +
                "(5, 'braise_10', 'Nêm lại cho vừa ăn: Nêm nếm lại gia vị, đun tiếp đến khi nước sánh lại thì tắt bếp. Dọn ra ăn kèm cơm hoặc rau sống.');";

        databaseSQLite.QuerySQL(INSERT_RECIPES_BRAISE);

        /*============================================================================================================*/

        String INSERT_RECIPES_SOUP = "INSERT OR IGNORE INTO Recipes VALUES " +
                "(1, 'soup_1', 'Sơ chế cá lóc: Cá lóc rửa sạch, cắt khúc vừa ăn, ướp với chút muối và hạt nêm trong 15 phút.')," +
                "(2, 'soup_1', 'Chuẩn bị nước me: Ngâm me với nước nóng, dầm lấy nước cốt.')," +
                "(3, 'soup_1', 'Nấu nước dùng: Phi hành tím với dầu ăn đến khi thơm, cho cà chua vào xào mềm. Đổ nước vào nồi, cho nước me vào đun sôi.')," +
                "(4, 'soup_1', 'Nấu canh: Khi nước sôi, cho cá lóc vào, nấu đến khi cá chín. Tiếp theo, thêm dứa, đậu bắp, bạc hà và giá đỗ vào nấu khoảng 5 phút.')," +
                "(5, 'soup_1', 'Nêm nếm gia vị: Thêm nước mắm, muối, đường, và hạt nêm vừa ăn. Cuối cùng, cho ngò om, ngò gai và ớt vào.')," +

                "(1, 'soup_2', 'Cắt rễ rau muống, loại bỏ lá bị hư, sau đó nhặt lấy phần ngọn và lá, thân thành từng khúc dài vừa ăn.')," +
                "(2, 'soup_2', 'Ngâm rau muống với 1 muỗng cà phê nước muối pha loãng trong khoảng 5 - 7 phút, sau đó rửa lại với nước sạch và để ráo.')," +
                "(3, 'soup_2', 'Bật bếp với lửa vừa, bạn cho 700ml nước vào nồi cùng 1 muỗng cà phê muối.')," +
                "(4, 'soup_2', 'Khi nước sôi, điều chỉnh lửa lớn, sau đó cho rau vào, dùng đũa nhấn cho rau ngập nước, tiến hành luộc rau trong 5 phút là có thể tắt bếp.')," +
                "(5, 'soup_2', 'Để cho nước luộc nguội bớt rồi cho vào 1 muỗng ăn cơm bột ngọt, 3 phần 4 muỗng ăn cơm muối, chanh vắt 1 quả rưỡi.')," +

                "(1, 'soup_3', 'Sơ chế nguyên liệu: Cà chua rửa sạch, bổ múi cau. Hành tím bóc vỏ, băm nhuyễn. Hành lá cắt nhỏ')," +
                "(2, 'soup_3', 'Phi thơm: Cho 1 muỗng canh dầu ăn vào nồi, phi thơm hành tím.')," +
                "(3, 'soup_3', 'Xào cà chua: Cho cà chua vào xào mềm khoảng 2–3 phút đến khi cà ra màu đỏ đẹp.')," +
                "(4, 'soup_3', 'Nấu canh: Đổ 1,5 lít nước vào nồi, đun sôi. Nếu dùng thịt băm, cho vào lúc nước bắt đầu sôi.')," +
                "(5, 'soup_3', 'Cho trứng (nếu dùng): Đánh tan trứng gà, đợi nước sôi lại rồi từ từ đổ trứng vào, khuấy nhẹ theo vòng tròn để trứng tơi.')," +
                "(6, 'soup_3', 'Nêm nếm: Nêm nước mắm, muối, bột ngọt vừa ăn. Nấu thêm 2 phút rồi tắt bếp. Rắc hành lá vào cho thơm.')," +

                "(1, 'soup_4', 'Sơ chế sườn bò: Sườn bò rửa sạch, loại bỏ bọt bẩn, chần qua nước sôi rồi rửa lại bằng nước lạnh để khử mùi hiệu quả')," +
                "(2, 'soup_4', 'Chuẩn bị rau củ: Củ cải trắng gọt vỏ, cắt khúc vừa ăn. Boa rô, nấm kim châm, hành lá để riêng')," +
                "(3, 'soup_4', 'Hầm sườn & nấu canh: Đun 1,5–2 lít nước, cho sườn bò và gừng, hành tây (nếu dùng), nấu sôi rồi hầm 15–20 phút. Thêm củ cải, nấu thêm 10 phút. Cuối cùng cho boa rô, nấm kim châm, nêm gia vị Maggi/nước tương, muối, đường, nấu 5 phút đến khi rau chín mềm.')," +
                "(4, 'soup_4', 'Hoàn thiện: Múc canh ra tô, rắc hành lá. Dùng lúc canh còn nóng, ăn kèm cơm trắng, miến hoặc bún đều rất hợp vị')," +

                "(1, 'soup_5', 'Sơ chế rau ngót: Nhặt sạch, bỏ cuống già, rửa với nước, vò nhẹ để rau mềm và giữ màu xanh mượt khi nấu')," +
                "(2, 'soup_5', 'Phi thơm hành: Phi hành khô với dầu ăn đến khi vàng thơm, tạo nền hương vị cho canh')," +
                "(3, 'soup_5', 'Xào thịt: Cho thịt băm vào xào săn cùng hành thơm, giúp thịt chín đều và tơi')," +
                "(4, 'soup_5', 'Đổ nước và nấu sôi: Thêm 1 lít nước, đun sôi, nhấc bọt nếu có, giữ nước trong')," +
                "(5, 'soup_5', 'Cho rau ngót vào: Khi nước sôi lần 2, thả rau vào nấu khoảng 1–2 phút đến khi rau chín nhưng vẫn giữ màu xanh đẹp')," +
                "(6, 'soup_5', 'Nêm gia vị & hoàn thiện: Nêm muối, nước mắm, hạt nêm, bột ngọt và tiêu. Tắt bếp ngay khi rau mềm, rắc hành lá, tiêu lên bề mặt')," +

                "(1, 'soup_6', 'Sơ chế xương: Rửa sạch xương heo với nước muối loãng. Chần qua nước sôi 2–3 phút để khử mùi, sau đó rửa lại với nước sạch')," +
                "(2, 'soup_6', 'Xào hành: Phi hành tím với dầu ăn đến khi thơm vàng')," +
                "(3, 'soup_6', 'Nấu nước dùng: Cho xương vào nồi, thêm khoảng 1,5–2 lít nước, nấu sôi và hớt bọt. Hầm xương khoảng 30–40 phút để nước ngọt')," +
                "(4, 'soup_6', 'Nấu canh: Cho khoai tây và cà rốt vào nấu cùng, đun lửa vừa khoảng 15 phút cho rau củ chín mềm.')," +
                "(5, 'soup_6', 'Nêm nếm gia vị: Nêm muối, hạt nêm, bột ngọt vừa ăn. Rắc hành lá, ngò rí và tiêu xay vào rồi tắt bếp')," +

                "(1, 'soup_7', 'Ướp thịt: Trộn thịt heo bằm với muối, tiêu, hạt nêm, ướp 10–15 phút.')," +
                "(2, 'soup_7', 'Phi hành: Làm nóng nồi, dùng dầu ăn phi thơm hành tím.')," +
                "(3, 'soup_7', 'Xào thịt: Cho thịt đã ướp vào đảo săn.')," +
                "(4, 'soup_7', 'Nấu canh: Thêm khoảng 1–1,2 lít nước, đun sôi rồi cho bí đỏ vào nấu 10–15 phút đến khi chín mềm.')," +
                "(5, 'soup_7', 'Nêm nếm & hoàn thiện: Nêm muối, bột nêm, bột ngọt và nước mắm (nếu dùng). Cuối cùng cho hành lá, ngò rí và tiêu trước khi tắt bếp.')," +

                "(1, 'soup_8', 'Sơ chế khổ qua: Rạch dọc khổ qua, bỏ ruột, ngâm vào nước muối loãng ~5–10 phút để giảm vị đắng, sau đó rửa sạch và để ráo')," +
                "(2, 'soup_8', 'Chuẩn bị nhân thịt: Trộn thịt bằm với mộc nhĩ, miến (nếu dùng), hành tím, tiêu, hạt nêm, nước mắm. Ướp khoảng 10–15 phút cho thấm')," +
                "(3, 'soup_8', 'Nhồi thịt: Nhồi nhân vào khổ qua, không quá chặt. Có thể dùng đầu hành lá cột lại để giữ nhân')," +
                "(4, 'soup_8', 'Nấu canh: Đun sôi ~1–1.5L nước, thêm chút muối/hạt nêm. Khi nước sôi già, thả khổ qua vào, vớt bọt, hạ lửa nhỏ và hầm ~25–30 phút đến khi khổ qua mềm')," +
                "(5, 'soup_8', 'Hoàn thiện: Nêm lại gia vị vừa ăn. Tắt bếp, rắc hành lá, ngò rí và tiêu trước khi múc ra')," +

                "(1, 'soup_9', 'Sơ chế và lọc cua: Rửa cua thật sạch, giã nhuyễn phần thân, lọc lấy nước cua, để lắng và gạn lấy phần trong. Lưu phần gạch cua riêng')," +
                "(2, 'soup_9', 'Chưng gạch cua: Phi thơm hành tím với dầu, thêm gạch cua vào đảo nhẹ trên lửa nhỏ đến khi dậy mùi, sau đó tắt bếp và để riêng')," +
                "(3, 'soup_9', 'Đun sôi nước cua: Cho nước cua đã lọc vào nồi, đun sôi, vớt bọt và giữ lửa vừa. Khi gạch nổi, vớt phần gạch thừa, gạch đã chưng vào nồi canh')," +
                "(4, 'soup_9', 'Cho rau muống: Khi nước sôi lần 2, cho rau muống vào, nấu khoảng 2–3 phút đến khi rau chín xanh và giòn. Không nên nấu quá lâu')," +
                "(5, 'soup_9', 'Nêm nếm và hoàn thiện: Nêm muối, hạt nêm, bột ngọt và một ít tiêu. Múc ra bát, rắc thêm tiêu và thêm gạch cua chưng lên trên cho dậy mùi. Dùng nóng, thưởng thức cùng cơm hoặc cà pháo muối')," +

                "(1, 'soup_10', 'Phi thơm hành: Làm nóng nồi với dầu, phi hành tím đến khi thơm vàng.')," +
                "(2, 'soup_10', 'Xào thịt: Cho thịt bằm vào đảo đều đến khi săn lại. Nếu dùng cà chua, xào cùng để nước dùng thêm ngọt và có màu đẹp.')," +
                "(3, 'soup_10', 'Nấu nước dùng: Thêm khoảng 1–1.2 lít nước, đun sôi. Vớt bọt (nếu có) để nước canh trong.')," +
                "(4, 'soup_10', 'Thả trứng (tuỳ chọn): Nếu dùng trứng, khuấy nhẹ rồi từ từ rưới trứng vào, tạo ruy‑băng đẹp mắt.')," +
                "(5, 'soup_10', 'Thêm lá tía tô: Khi canh sôi lần cuối, hạ lửa và thả lá tía tô vào, nấu nhanh khoảng 30 giây – 1 phút, đủ để lá chín nhưng vẫn giữ màu sắc.')," +
                "(6, 'soup_10', 'Nêm nếm & hoàn thiện: Nêm muối, hạt nêm, bột ngọt và một ít tiêu. Múc ra tô, thưởng thức khi còn nóng');";
        databaseSQLite.QuerySQL(INSERT_RECIPES_SOUP);

        /*============================================================================================================*/

        String INSERT_RECIPES_FRY = "INSERT OR IGNORE INTO Recipes VALUES " +
                "(1, 'fry_1', 'Sơ chế & ướp thịt: Thịt bằm ướp cùng muối, tiêu, hạt nêm, ướp khoảng 10 phút để đậm vị.')," +
                "(2, 'fry_1', 'Đánh trứng: Đập trứng vào tô, cho thịt đã ướp, hành tím, hành lá vào. Đánh đều đến khi lòng trắng và đỏ hòa quyện, bọt khí ít.')," +
                "(3, 'fry_1', 'Chiên trứng: Làm nóng chảo với dầu ăn, phi thơm hành tím. Đổ hỗn hợp trứng vào, nướng lửa vừa. Khi mép bắt đầu vàng, dùng spatula gấp mép vào giữa để trứng chín đều, sau đó lật mặt kia.')," +
                "(4, 'fry_1', 'Hoàn thiện & trình bày: Chiên đến khi hai mặt vàng đều, trứng được xốp mềm bên trong. Cho ra đĩa, rắc thêm hành lá hoặc tiêu nếu thích.')," +

                "(1, 'fry_2', 'Chuẩn bị cơm và nguyên liệu: Cơm nguội tơi hạt, để trong tủ lạnh 30 phút nếu cần. Thái lạp xưởng, băm nhỏ tỏi, hành. Rửa rau củ và để ráo.')," +
                "(2, 'fry_2', 'Xào nguyên liệu: Phi tỏi + hành tím với dầu + bơ đến thơm. Xào lạp xưởng và tôm/thịt băm (nếu dùng) đến săn thơm. Thêm rau củ vào đảo nhanh tay ~2 phút.')," +
                "(3, 'fry_2', 'Trứng chiên sơ: Đẩy hỗn hợp sang một bên, đổ trứng vào, đánh tan rồi để trứng lắng từ từ. Khi trứng chín sơ, trộn đều cơm vào cùng gà và rau.')," +
                "(4, 'fry_2', 'Nêm gia vị: Thêm nước mắm, xì dầu, tiêu, chút đường, dầu mè (nếu có). Lúc này nêm nếm sao cho vừa vị đậm – ngọt nhè nhẹ.')," +
                "(5, 'fry_2', 'Chiên giòn hạt: Giảm lửa lớn, chiên thêm 2–3 phút cho hạt cơm săn lại, hơi khét vàng đẹp.')," +
                "(6, 'fry_2', 'Hoàn thiện & thưởng thức: Nhấc chảo ra, rắc hành lá, tiêu. Múc ra đĩa, có thể kèm dưa leo, cà chua hoặc đồ chua')," +

                "(1, 'fry_3', 'Sơ chế & trộn nhân: Nhúng tất cả rau củ đã rửa ráo vào bát nhân, thêm thịt xay, mộc nhĩ/nấm, miến. Ướp với muối, hạt nêm, mì chính, tiêu. Trộn đều, để 5–10 phút.')," +
                "(2, 'fry_3', 'Phết giấm/đường vỏ bánh: Trên bánh đa nem, phết nhẹ giấm hoặc đường pha loãng để vỏ vàng giòn, không rách khi cuốn.')," +
                "(3, 'fry_3', 'Cuốn nem: Múc lượng nhân (~1 muỗng canh) vào vỏ, cuốn kín chặt tay, gập hai đầu và dùng lõi lòng trắng trứng để cố định.')," +
                "(4, 'fry_3', 'Chiên nem: Đun dầu ở lửa trung bình: khi dầu sủi tăm, cho nem vào chiên lần 1 đến khi vàng nhẹ, vớt ra để ráo. Rán lần 2 lửa lớn thêm vài phút đến vàng giòn và ráo dầu.')," +
                "(5, 'fry_3', 'Hoàn thiện: Vớt nem ra giấy thấm dầu. Dùng nóng với rau sống, bún, và nước chấm chua ngọt (nước mắm, giấm, đường, tỏi, ớt).')," +

                "(1, 'fry_4', 'Ướp thịt: Thịt xay trộn muối, tiêu, nước mắm, hạt nêm; ướp ~10 phút để ngấm')," +
                "(2, 'fry_4', 'Chuẩn bị hỗn hợp trứng: Đập trứng vào tô, cho hành lá + thịt ướp + bột ngọt, nước mắm; đánh đều, rút bớt bọt khí')," +
                "(3, 'fry_4', 'Chiên trứng: Làm nóng chảo, cho dầu và hành tím vào phi thơm. Đổ hỗn hợp trứng vào chảo, dàn đều. Giữ lửa vừa. Khi mép trứng bắt đầu đông và vàng nhẹ, đậy nắp giúp trứng chín đều. Dùng cây lật nhẹ để trứng rời chảo, chiên thêm ~30 giây mỗi mặt đến chín vàng giòn.')," +
                "(4, 'fry_4', 'Hoàn thiện: Cho trứng ra đĩa, rắc tiêu và hành lá nếu thích')," +

                "(1, 'fry_5', 'Sơ chế: Cà chua rửa sạch, bỏ hạt (nếu thích), thái hạt lựu. Hành tím băm nhỏ, hành lá thái.')," +
                "(2, 'fry_5', 'Chuẩn bị trứng: Đập trứng vào tô, thêm muối, hạt nêm, bột ngọt, tiêu. Thêm cà chua, hành lá, đánh nhẹ nhàng cho hỗn hợp hoà quyện')," +
                "(3, 'fry_5', 'Chiên trứng: Đun nóng dầu ăn (và dầu điều nếu dùng) trên chảo, phi thơm hành tím. Đổ hỗn hợp trứng – cà chua vào, chiên với lửa vừa. Khi mép trứng bắt đầu vàng nhẹ, đậy nắp để trứng chín đều')," +
                "(4, 'fry_5', 'Hoàn thiện: Khi mặt dưới vàng đều, lật chiên phía trên thêm ít phút. Tắt bếp, rắc tiêu và hành lá tùy ý. Món ăn có thể dùng ngay với cơm nóng.')," +

                "(1, 'fry_6', 'Khử mùi & ướp gà: Rửa cánh gà sạch, chà xát muối–giấm hoặc chanh/gừng để khử mùi. Ướp với muối, tiêu, hạt nêm, bột bắp, nước mắm, đường, dầu hào, tương ớt, tỏi (và gừng nếu thích) khoảng 15–20 phút.')," +
                "(2, 'fry_6', 'Chiên cánh gà: Đun dầu nóng ở lửa vừa. Chiên lần 1 đến khi cánh hơi vàng, vớt ra để ráo. Chiên lần 2 ở lửa lớn đến khi vàng giòn đều.')," +
                "(3, 'fry_6', 'Làm sốt & rim gà: Dùng 1 muỗng dầu, phi thơm tỏi và ớt. Pha hỗn hợp với nước mắm, đường, dầu hào, tương ớt và chút nước lọc, đun sôi đến khi sệt. Cho cánh gà vào, đảo nhẹ tay để cánh thấm đều sốt, rim thêm 2–3 phút.')," +
                "(4, 'fry_6', 'Hoàn thiện: Vớt ra đĩa, rắc chút tiêu hoặc tỏi phi (nếu có). Ăn nóng cùng cơm hoặc dùng làm mồi nhậu.')," +

                "(1, 'fry_7', 'Gọt & ngâm: Gọt vỏ, cắt khoai thành thanh đều tay. Ngâm nước lạnh pha muối ít nhất 30 phút (hoặc ngâm nước đá 15 phút) để loại bỏ tinh bột, giúp khoai giòn hơn.')," +
                "(2, 'fry_7', 'Luộc sơ: Luộc khoai trong nước sôi khoảng 2 phút, sau đó ngâm ngay vào nước đá/lạnh 2 phút → giúp khoai chín đều, không sống ruột.')," +
                "(3, 'fry_7', 'Chiên hai lần: Lần 1 (lửa vừa): chiên đến khi khoai hơi vàng nhạt (~3–4 phút), vớt ra để nguội. Lần 2 (lửa lớn): chiên đến vàng giòn (1–2 phút).')," +
                "(4, 'fry_7', 'Hoàn thiện: Vớt khoai ra, thấm dầu. Rắc muối, tiêu, phô mai, oregano, hoặc dùng với tương cà/tương ớt theo sở thích.')," +

                "(1, 'fry_8', 'Sơ chế cá & khử mùi: Rửa cá sạch, lau khô hoàn toàn. Dùng muối chà nhẹ da/ruột cá để khử nhớt, sau đó rửa lại và thấm khô.')," +
                "(2, 'fry_8', 'Tẩm bột & ướp gia vị: Cho một lớp mỏng bột chiên giòn hoặc bột năng lên cá (có thể ướp thêm tiêu, muối). Trường hợp làm sốt, chỉ cần ướp nhẹ để thấm bên trong.')," +
                "(3, 'fry_8', 'Chiên cá giòn: Bắc chảo dầu nóng đến khi sôi tăm, thả cá vào chiên lửa vừa. Chiên cẩn thận mỗi mặt cho đến khi vàng giòn, vớt ra để ráo dầu.')," +
                "(4, 'fry_8', 'Làm sốt (tuỳ chọn): Phi thơm tỏi, hành, ớt. Pha nước mắm/đường chua ngọt hoặc tương ớt, thêm cà chua/dứa nếu thích; đun đến khi sền sệt. Cho cá vào rim nhẹ để thấm đều.')," +
                "(5, 'fry_8', 'Hoàn thiện: Cho cá ra đĩa, rắc hành lá, tiêu, dùng nóng với cơm trắng hoặc rau sống.')," +

                "(1, 'fry_9', 'Sơ chế & Ướp thịt: Trộn thịt xay với hành tím, tỏi, hành lá, bột chiên, rưới chút nước mắm, muối, tiêu, đường. Nhồi đều, để ngấm 15–20 phút')," +
                "(2, 'fry_9', 'Tạo hình chả: Viên từng viên chả dẹt hoặc ép thành miếng tròn mỏng, dùng trứng đánh xung quanh để tạo độ kết dính và màu đẹp.')," +
                "(3, 'fry_9', 'Chiên giòn: Đun dầu nóng đến khi sủi tăm nhỏ. Chiên chả lần 1 ở lửa vừa tới khi vỏ hơi vàng, vớt ra để ráo. Chiên lần 2 ở lửa lớn thêm vài phút để vỏ thật giòn')," +
                "(4, 'fry_9', 'Hoàn thiện: Vớt chả ra giấy thấm dầu. Thưởng thức nóng với cơm, bánh mì, bún hoặc rau sống tùy thích. Các loại nước chấm phù hợp: chua ngọt, tương ớt, cay chấm.');";

        databaseSQLite.QuerySQL(INSERT_RECIPES_FRY);

        /*============================================================================================================*/

        String INSERT_RECIPES_DESSERT = "INSERT OR IGNORE INTO Recipes VALUES " +
                "(1, 'dessert_1', 'Ngâm & sơ chế nguyên liệu: Ngâm gạo nếp với chút muối từ 6–12 giờ, để ráo. Ngâm  đậu xanh 3–4 giờ, hấp hoặc đồ chín, nghiền nhuyễn, nêm chút muối. Thịt ba chỉ thái miếng dày 1cm, ướp mỗi miếng với muối, tiêu, nước mắm, tẩm chút đường, để khoảng 30 phút.')," +
                "(2, 'dessert_1', 'Chuẩn bị lá dong & lạt: Lá dong rửa sạch, cắt gọn, luộc sơ để mềm, lau khô. Lạt ngâm mềm.')," +
                "(3, 'dessert_1', 'Gói bánh: Xếp 4–6 lá dong vuông vức, lớp dưới úp, lớp trên ngửa. Cho 1 lớp gạo → đậu xanh → 1 miếng thịt → đậu xanh → gạo. Gói chắc tay để bánh vuông và không bị bung khi luộc. Buộc chéo lạt chặt 2 chiều.')," +
                "(4, 'dessert_1', 'Luộc bánh: Xếp bánh vào nồi lớn có lót lá dưới đáy, ngập nước. Luộc từ 8–12 giờ, thêm nước sôi mỗi khi cạn, giữ lửa đều.')," +
                "(5, 'dessert_1', 'Hoàn thiện: Vớt bánh, để ráo và để nguội. Cắt thành từng miếng khi ăn. Dùng nóng với dưa hành, giò lụa, chả… càng thêm trọn vị Tết.')," +

                "(1, 'dessert_2', 'Ngâm & sơ chế nguyên liệu: Gạo nếp và đậu xanh ngâm đủ thời gian, sau đó vo sạch để ráo. Thịt ba chỉ rửa, ướp muối, tiêu, đường, nước mắm, để thấm ~30 phút')," +
                "(2, 'dessert_2', 'Chuẩn bị lá & lạt: Lá dong/chuối trụng sơ cho mềm, lau khô. Lạt ngâm nước cho dễ buộc')," +
                "(3, 'dessert_2', 'Gói bánh: Xếp lá đo hình trụ, cho gạo → đậu xanh → thịt → đậu xanh → gạo. Cuốn chặt tay rồi buộc chắc bằng lạt theo chiều ngang và dọc.')," +
                "(4, 'dessert_2', 'Luộc bánh: Xếp bánh vào nồi, đổ ngập nước, luộc 8–12 giờ, chú ý thêm nước sôi để bánh chín đều.')," +
                "(5, 'dessert_2', 'Hoàn thiện: Vớt bánh ra, để ráo khoảng 30 phút rồi cắt lát, thưởng thức cùng dưa hành hoặc chấm mật mía.')," +

                "(1, 'dessert_3', 'Trộn bột: Trong tô lớn, trộn bột nếp, bột năng, muối. Từ từ thêm nước ấm (hoặc sữa ấm), vừa rót vừa nhồi đến khi thành khối bột dẻo mịn, không dính tay.')," +
                "(2, 'dessert_3', 'Ủ bột: Bọc kín tô bằng màng bọc thực phẩm, để nghỉ khoảng 20–30 phút để bột mềm, đàn hồi tốt.')," +
                "(3, 'dessert_3', 'Tạo hình bánh: Chia bột thành các phần bằng nhau rồi cán hoặc vo tròn, dẹt nhẹ. Phủ khăn ẩm để bánh không khô.')," +
                "(4, 'dessert_3', 'Hấp bánh: Xếp bánh lên lá chuối hoặc giấy nến (thấm dầu nhẹ). Hấp trên nồi nước sôi, duy trì hơi nóng trung bình, khoảng 10–12 phút đến khi bánh chuyển từ trắng trong sang đục mờ, chín hoàn toàn.')," +
                "(5, 'dessert_3', 'Hoàn thiện: Để bánh nguội bớt, đặt giò lụa giữa hai miếng hoặc kẹp giò và chấm chút muối vừng/ tiêu tùy thích. Bánh dẻo mềm, thơm bùi, giò béo mặn hài hòa.')," +

                "(1, 'dessert_4', 'Ngâm & xử lý gạo nếp: Ngâm nếp cùng nước tro tàu 8–12 giờ, sau đó xả sạch, để ráo.')," +
                "(2, 'dessert_4', 'Làm nhân đậu xanh: Luộc đậu đến khi mềm → nghiền nhuyễn → trộn đường + xào nhỏ lửa đến khi sền sệt → để nguội và viên nhân.')," +
                "(3, 'dessert_4', 'Chuẩn bị lá & gói bánh: Lá tre/chuối trụng mềm, lau sạch lau khô. Xếp 2 lá tạo phễu tam giác → cho lớp nếp, viên đậu, thêm nếp phủ kín → gói chặt, buộc dây chắc hình chóp.')," +
                "(4, 'dessert_4', 'Luộc bánh: Luộc bánh trong nước sôi khoảng 2–3 giờ (tùy kích thước), châm thêm nước khi cạn. Vớt ra → xối nước lạnh → để ráo.')," +
                "(5, 'dessert_4', 'Hoàn thiện: Ăn bánh nguội hoặc hấp lại, dùng kèm đường mật, nước cốt dừa hoặc xì dầu chấm nhẹ theo sở thích.')," +

                "(1, 'dessert_5', 'Pha bột & hấp bánh: Hoà bột gạo, bột năng, muối với nước, đánh tan. Lọc mịn, để nghỉ 30 phút. Phết dầu vào từng chén nhỏ, đổ bột khoảng 2/3 chén. Hấp cách thuỷ 6–8 phút đến khi bánh chuyển trắng đục và có vết xoáy trên bề mặt.')," +
                "(2, 'dessert_5', 'Chuẩn bị nhân: Đậu xanh hấp rồi nghiền nhuyễn, nêm chút muối/đường. Tôm khô xào với ít dầu đến khô ráo, tơi thơm. Phi hành lá với dầu nóng tạo mỡ hành.')," +
                "(3, 'dessert_5', 'Làm nước chấm: Hoà nước mắm với đường, tỏi, ớt, chanh để tạo nước chấm đậm vị ngọt - chua - mặn.')," +
                "(4, 'dessert_5', 'Hoàn thiện: Múc từng chén nhỏ: bánh – đậu xanh – tôm – mỡ hành – (tỏi ớt băm). Khi ăn chan nước mắm và thưởng thức ngay.')," +

                "(1, 'dessert_6', 'Nhào bột: Trộn bột nếp và bột tẻ (nếu dùng), thêm một ít muối. Rót từ từ nước ấm, nhào đến khi bột mịn, dẻo, không dính tay. Ủ khoảng 20–30 phút.')," +
                "(2, 'dessert_6', 'Chuẩn bị nhân: Nhân đường: dùng khối đường phên đã cắt. Nhân đậu xanh: đậu ngâm, hấp chín, nghiền nhuyễn rồi trộn chút đường, vo viên nhỏ.')," +
                "(3, 'dessert_6', 'Vo bánh: Chia bột thành các viên nhỏ, ấn dẹp, cho nhân vào giữa rồi vê kín. Giữ bọc kín để bánh không rách khi luộc.')," +
                "(4, 'dessert_6', 'Luộc bánh: Đun sôi nước, nhẹ nhàng thả bánh vào. Khi bánh nổi lên là chín. Vớt bánh thả ngay vào bát nước lạnh khoảng 2–3 phút để bánh không dính nhau.')," +
                "(5, 'dessert_6', 'Nấu nước đường: Đun sôi khoảng 200–300 ml nước, cho gừng sợi, đường (hoặc đường thốt nốt) tan. Hòa bột năng với ít nước rồi từ từ đổ vào, khuấy đều đến khi nước sánh nhẹ. Thêm nước cốt dừa nếu thích.')," +
                "(6, 'dessert_6', 'Trình bày: Cho bánh vào bát, chan nước đường gừng, rắc mè rang và dừa nạo. Dùng khi còn ấm, thơm vị gừng và dẻo mềm, ấm bụng.')," +

                "(1, 'dessert_7', 'Kích hoạt & ủ bột: Hoà men với sữa ấm, đường, rồi để 5–10 phút cho men nổi bọt. Trộn cùng bột, nước ấm, dầu, muối, nhào đến khi bột mịn không dính tay. Ủ bột ở nơi ấm trong 1giờ đến khi nở gấp đôi.')," +
                "(2, 'dessert_7', 'Chuẩn bị nhân: Xào hành tím phi thơm, cho thịt vào xào săn. Thêm mộc nhĩ, nấm, miến, cà rốt, lạp xưởng, nêm nước mắm, dầu hào, đường, tiêu, hạt nêm. Vo nhân thành từng viên, nhồi trứng cút vào giữa.')," +
                "(3, 'dessert_7', 'Tạo hình bánh: Nhồi bột đã nở, chia thành 10 phần ~50 g. Cán mỏng, cho nhân vào giữa, túm khéo kín mép bánh, tạo hình tròn hoặc rút ly. Đặt trên giấy nến, ủ thêm 15–20phút.')," +
                "(4, 'dessert_7', 'Hấp bánh: Hấp bằng nồi hấp có lót khăn ẩm, hấp lửa vừa 15–20 phút. Mở hé nắp, lấy bánh ra.')," +
                "(1, 'dessert_8', 'Chuẩn bị nguyên liệu: Gạo nếp ngâm 6–8 giờ, vo sạch, để ráo. Đậu xanh hấp chín, nghiền nhuyễn, trộn chút muối. Gấc bổ, lấy thịt, trộn rượu trắng tách hạt, trộn đều với nếp.')," +
                "(2, 'dessert_8', 'Làm nhân: Đậu xanh đã nghiền xào với đường và dầu ăn đến khi dẻo, để nguội rồi vo viên nhỏ.')," +
                "(3, 'dessert_8', 'Tạo hình bánh: Chia bột nếp gấc thành phần đều, ấn dẹp, đặt viên nhân vào giữa và vo tròn lại.')," +
                "(4, 'dessert_8', 'Luộc bánh: Đun sôi nước, thả bánh vào. Khi bánh nổi là chín, vớt ra ngâm nước lạnh 3 phút rồi vớt ra để ráo.')," +
                "(5, 'dessert_8', 'Lăn mè: Lăn bánh qua mè rang chín để tăng hương vị và tạo lớp áo đẹp.')," +
                "(6, 'dessert_8', 'Trình bày: Bánh dẻo thơm, màu đỏ cam đẹp mắt. Dùng lúc nguội hoặc hơi ấm, kèm trà nóng càng ngon.')," +

                "(1, 'dessert_9', 'Chuẩn bị bột: Trộn bột năng, bột gạo, đường và nước lá dứa. Khuấy đều, lọc qua rây, để nghỉ 15–20 phút.')," +
                "(2, 'dessert_9', 'Làm nhân: Dừa nạo trộn với đường, xíu muối, xào nhỏ lửa đến khi ráo và dẻo.')," +
                "(3, 'dessert_9', 'Đổ bánh: Đun khuôn hoặc đĩa hấp, quét lớp dầu, đổ lớp bột mỏng → hấp chín (2–3 phút) → thêm 1 lớp nhân → đổ lớp bột tiếp. Lặp lại 5–7 lớp.')," +
                "(4, 'dessert_9', 'Hấp & làm nguội: Hấp chín toàn bộ bánh khoảng 20 phút. Để nguội hoàn toàn trước khi cắt.')," +
                "(5, 'dessert_9', 'Trình bày: Cắt bánh thành miếng vuông hoặc hình thoi. Dùng nguội, bánh dai thơm, béo dừa, có màu xanh bắt mắt từ lá dứa.')," +

                "(1, 'dessert_10', 'Chuẩn bị nguyên liệu: Dừa nạo, gừng thái sợi nhỏ, đường trắng (hoặc đường thốt nốt), vani nếu thích.')," +
                "(2, 'dessert_10', 'Nấu dừa: Trộn đều dừa với đường và gừng, để ngấm 15 phút. Sau đó cho lên chảo không dính, sên nhỏ lửa.')," +
                "(3, 'dessert_10', 'Sên mứt: Liên tục đảo đều tay, khi đường bắt đầu keo lại thì hạ lửa. Đảo đến khi đường kết tinh trắng bao quanh sợi dừa là đạt.')," +
                "(4, 'dessert_10', 'Hoàn thiện: Rải mứt ra mâm cho nguội hẳn, bảo quản lọ kín, nơi khô ráo. Có thể thêm vài giọt vani cuối cùng khi sên.')," +

                "(1, 'dessert_11', 'Chuẩn bị nguyên liệu: Cùi bưởi rửa sạch, cắt hạt lựu. Ngâm nước muối loãng 1–2h rồi bóp xả nhiều lần cho bớt đắng.')," +
                "(2, 'dessert_11', 'Luộc & ướp: Luộc cùi bưởi 1–2 lần đến khi không còn vị the đắng. Trộn đường, để ngấm 30 phút rồi áo 1 lớp bột năng.')," +
                "(3, 'dessert_11', 'Luộc cùi bưởi: Thả vào nước sôi, khi nổi thì vớt ra ngâm nước đá. Sau đó để ráo.')," +
                "(4, 'dessert_11', 'Nấu chè: Nấu đậu xanh đã hấp chín với nước, đường. Cho cùi bưởi vào cuối cùng, khuấy nhẹ tay. Hoà tan ít bột năng rồi đổ vào tạo độ sánh.')," +
                "(5, 'dessert_11', 'Thưởng thức: Múc ra bát, thêm nước cốt dừa và đá bào nếu thích ăn lạnh. Vị thanh mát, dai giòn sần sật hấp dẫn.');";

        databaseSQLite.QuerySQL(INSERT_RECIPES_DESSERT);

        /*============================================================================================================*/

        String INSERT_RECIPES_FOREIGN = "INSERT OR IGNORE INTO Recipes VALUES " +
                "(1, 'foreign_1', 'Luộc mì: Đun sôi nước nhiều muối (như nước biển), cho mì luộc al dente (~8–10 phút), giữ lại 1 chén nước luộc mì.')," +
                "(2, 'foreign_1', 'Phi tỏi vàng: Đun dầu ô liu lửa nhỏ, cho tỏi + ớt khô nếu dùng, phi đến thơm và hơi vàng.')," +
                "(3, 'foreign_1', 'Trộn mì: Vớt mì, cho vào chảo dầu tỏi, thêm chút nước luộc để tạo sốt keo. Nêm muối, tiêu, rắc ngò/parmesan cuối cùng.')," +
                "(4, 'foreign_1', 'Hoàn thành: Múc ra đĩa, có thể thêm phô mai, rau thơm, dùng nóng.')," +

                "(1, 'foreign_2', 'Nhồi & ủ bột: Trộn men + đường + nước, để yên 5 phút cho men nổi bọt. Trộn bột + muối, đổ men nước + dầu, nhồi khoảng 8–10 phút đến khi mịn, không dính. Ủ bột ở nơi ấm khoảng 1 giờ đến khi nở gấp đôi.')," +
                "(2, 'foreign_2', 'Sốt cà chua: Phi nhẹ tỏi trong dầu ô liu, thêm cà chua, muối, đường, oregano. Nấu 5–7 phút, tắt bếp.')," +
                "(3, 'foreign_2', 'Tạo vỏ bánh: Nhồi bột đã ủ để xẹp bọt khí, vê thành 2 quả (120g mỗi quả). Cán mỏng thành đĩa ~25 cm (kiểu New York/Margherita) hoặc dày hơn với mép viền rõ ràng (Chicago/Deep‑dish). Bày lên khay nướng có rắc lót đá pizza hoặc semolina.')," +
                "(4, 'foreign_2', 'Bố trí topping: Phết sốt lên vỏ, rắc phô mai, và xếp topping lên trên. Có thể rắc thêm oregano khô hoặc basil sau khi nướng.')," +
                "(5, 'foreign_2', 'Nướng bánh: Làm nóng lò trước ở 250–280 °C (lò gia đình). Nếu có đá pizza, làm nóng cùng khay. Nướng 10–12 phút hoặc đến khi phô mai chảy đều, mép bánh giòn vàng. Pizza Margherita kiểu Neapolitan chín nhanh ở nhiệt độ cao, vỏ giòn nhưng vẫn mềm mượt.')," +

                "(1, 'foreign_3', 'Trộn & nặn pât té: Cho thịt + muối, tiêu, Worcestershire (và tỏi/hành bột nếu dùng). Trộn nhẹ, tránh làm bị chặt. Nặn 4 patties (~125 g mỗi miếng), dày ~1.5cm, khoét nhẹ hõm giữa để tránh bị phồng khi nấu')," +
                "(2, 'foreign_3', 'Ướp trong 20 phút: Cho vào tủ lạnh giúp giữ hình dạng khi nấu.')," +
                "(3, 'foreign_3', 'Nấu patties: Bật bếp trung bình–cao. Áp chảo/dùng nồi gang/điện/hotplate. Nướng mỗi mặt ~4–6 phút (hoặc đến nhiệt độ 71°C). Trong 1 phút cuối, đặt phô mai lên để làm cheeseburger.')," +
                "(4, 'foreign_3', 'Nướng bánh mì: Làm nóng bánh mì vài phút trong chảo với chút bơ/olive oil.')," +
                "(5, 'foreign_3', 'Hoàn thiện: Kẹp thịt + phô mai giữa hai nửa bánh mì, thêm rau củ, sauces: ketchup, mayo, mustard, BBQ theo khẩu vị. Ăn nóng để cảm nhận độ mềm, béo, rau mát và sốt đậm đà.')," +

                "(1, 'foreign_4', 'Chuẩn bị bột: Trộn bột, baking powder với dashi, trứng, muối, xì dầu. Trộn đều tới khi mịn, không còn vón cục. Nên ủ bột khoảng 20–30 phút để cảm biến dashi thấm đều, hỗn hợp hơi lỏng mềm, giúp bánh mềm mại bên trong mà giòn bên ngoài khi nướng')," +
                "(2, 'foreign_4', 'Gia nhiệt & phết dầu: Làm nóng chảo đến ~200°C, dùng chổi phết dầu thực vật vào từng khuôn. Ngay khi nóng đều, đổ đầy bột đến khoảng 70–80% khuôn')," +
                "(3, 'foreign_4', 'Thêm nhân & tạo viên: Thả từng miếng bạch tuộc vào từng ô bột, rải hành lá, beni shoga, tenkasu. Đổ thêm chút bột nếu cần để hơi tràn mép khuôn. Khi viền ngoài bột bắt đầu chín nhẹ, dùng que lật từng viên để tạo hình tròn đẹp và chín đều (~5–8 phút).')," +
                "(4, 'foreign_4', 'Hoàn thành & trang trí: Khi bánh vàng đều, lấy ra ngay cho nóng. Quét takoyaki sauce, vẽ đường mayonnaise, rắc aonori và katsuobushi. Ăn ngay để giữ độ giòn ngoài và mềm trong, thơm ngậy sốt và topping “tan” nhẹ trên bánh.')," +

                "(1, 'foreign_5', 'Nấu cơm và trộn giấm: Nấu cơm Nhật (gạo tròn), trộn giấm sushi khi cơm còn nóng. Để nguội hoàn toàn.')," +
                "(2, 'foreign_5', 'Chuẩn bị nguyên liệu: Thái lát cá, rau củ mỏng dài.')," +
                "(3, 'foreign_5', 'Cuộn sushi: Trải rong biển lên mành tre, dàn mỏng lớp cơm (~1 cm), xếp nhân dọc 1/3 tấm. Cuộn chặt tay, giữ đều tay và cắt cuộn bằng dao bén thấm nước.')," +
                "(4, 'foreign_5', 'Trang trí và thưởng thức: Cắt thành 6–8 khoanh, dọn kèm xì dầu, wasabi và gừng hồng.')," +

                "(1, 'foreign_6', 'Chọn cá & bảo quản: Chỉ dùng cá sashimi-grade, giữ lạnh đến khi thái để đảm bảo an toàn và độ tươi.')," +
                "(2, 'foreign_6', 'Kỹ thuật thái cá: Thái ngang thớ (against the grain), kiểu hirazukuri dày ~8–10mm, hoặc usuzukuri mỏng nhẹ. Kỹ thuật cắt nghiêng 45° theo hướng kéo chứ không cưa để có lát cắt mịn, sắc nét.')," +
                "(3, 'foreign_6', 'Trình bày sashimi: Xếp lát cá lên củ cải bào, xen kẽ với lá shiso, trang trí thêm rong biển hoặc hoa cho đẹp mắt. Cho wasabi cạnh sashimi, phục vụ cùng xì dầu chấm, gừng ngâm.')," +

                "(1, 'foreign_7', 'Đánh trứng: Đập 2–3 quả trứng, thêm chút muối, tiêu và có thể 1 tsp nước hoặc sữa để omelet mềm hơn')," +
                "(2, 'foreign_7', 'Làm nóng chảo: Trên bếp lửa vừa, cho bơ tan để bề mặt chảo nóng đều trước khi đổ trứng.')," +
                "(3, 'foreign_7', 'Rán trứng: Đổ trứng đã đánh vào, dùng đũa hoặc spatula nhẹ bay bọt, giữ cho omelet mềm mịn (kiểu Pháp). Khi trứng gần đông đến đáy nhưng vẫn mềm mặt trên (~30–45s), rắc phô mai và nhân nếu dùng, đợi thêm 1 phút để phô mai tan.')," +
                "(4, 'foreign_7', 'Gập & hoàn thiện: Gập omelet lại làm đôi hoặc cuộn nhẹ. Dùng lửa nhỏ để tránh mặt ngoài cháy quá. Tắt bếp, bày ngay ra đĩa, ăn nóng.')," +

                "(1, 'foreign_8', 'Chuẩn bị cơm & nêm: Cơm mới nấu còn nóng, trộn nhẹ với muối và dầu mè hoặc giấm để tăng hương vị, giúp cơm bám chắc. Nếu dùng muối vừng, trộn chung trước khi nắm.')," +
                "(2, 'foreign_8', 'Viên cơm: Cho một lượng cơm khoảng nắm tay, đặt nhân vào giữa (ví dụ 2 tsp cá ngừ mayo). Dùng khuôn hoặc tay nhúng nước/giấm, nắm chặt thành hình tam giác, trụ hoặc viên tròn, đảm bảo chặt và giữ form.')," +
                "(3, 'foreign_8', 'Gói & hoàn thiện: Bọc ngoài bằng rong biển nori (phiên bản Nhật), hoặc để trần nếu là cơm nắm Việt. Có thể rắc mè rang, muối vừng bên ngoài để tăng hương vị.')," +
                "(4, 'foreign_8', 'Bảo quản & thưởng thức: Cho vào túi kín hoặc hộp cơm. Cơm nắm nên được ăn trong ngày để giữ độ mềm, không bị khô.')," +

                "(1, 'foreign_9', 'Xào thịt: Phi dầu + hành + tỏi ở lửa vừa → cho thịt vào xào cho chín và tách mỡ. Thêm ớt bột, cumin, paprika, muối, tiêu; đổ salsa → simmer trong 3–5 phút đến khi sốt hơi sệt.')," +
                "(2, 'foreign_9', 'Làm nóng tortilla: Cho tortilla vào chảo không dầu hoặc lò nướng ở 180°C trong 2–3 phút cho mềm dẻo. Hard‑shell tortilla có thể nướng giòn tương tự.')," +
                "(3, 'foreign_9', 'Bày tacos: Múc thịt lên tortilla, thêm xà lách, cà chua, hành, cilantro và phô mai. Rưới guacamole, sour cream hoặc salsa tuỳ thích.')," +
                "(4, 'foreign_9', 'Thưởng thức: Dùng nóng, kèm lát chanh/vắt thêm, để tăng hương vị.')," +

                "(1, 'foreign_10', 'Sơ chế & ướp gà: Rửa sạch, để ráo. Giã nhuyễn sả, tỏi, hành, gừng; trộn thêm muối, tiêu, ngũ vị, dầu ô liu. Ướp gà từ 1–2 giờ, có thể để qua đêm cho ngấm sâu.')," +
                "(2, 'foreign_10', 'Gói gà: Trải lá chuối, đặt gà ướp lên, gói lại. Quấn lớp giấy bạc để giữ ẩm, tiếp đến là một lớp đất sét phủ kín gà và giấy bạc.')," +
                "(3, 'foreign_10', 'Nướng gà: Làm nóng than hoa/lò nướng. Đặt gà vào giữa, nướng ở nhiệt độ thấp (120–150°C), quay đều cho đất sét nứt sau ~2 giờ. Khi vỏ sét nứt, tháo gói, nướng thêm 5–10 phút để da gà giòn.')," +
                "(4, 'foreign_10', 'Trình bày & thưởng thức: Đập lớp đất sét vỡ để lộ gà bên trong. Thưởng thức nóng cùng rau sống, chấm tương ớt hoặc muối tiêu chanh.');";

        databaseSQLite.QuerySQL(INSERT_RECIPES_FOREIGN);
    }

    // Hàm chạy chung các hàm thêm
    public static  void InsertDatabaseAll(Context context){
        InsertData_Account(context);
        InsertData_Tag(context);
        InsertData_Food(context);
        InsertData_Ingredients(context);
        InsertData_Recipes(context);
    }
    public static void getDataByTagSpinner(DatabaseSQLite databaseSQLite,Adapter adapter, List<Food> arr_food,String tag){
        Cursor cursor;
        arr_food.clear();
        cursor=databaseSQLite.getCursorData("SELECT * FROM FOOD f JOIN TAG t ON f.type_food=t.id_tag WHERE t.name_tag='"+tag+"';");
        while (cursor.moveToNext())
        {
            String id=cursor.getString(0);
            String name=cursor.getString(1);
            Double dif=cursor.getDouble(2);
            String img_path=cursor.getString(3);
            String desc=cursor.getString(4);
            String link=cursor.getString(5);
            String note=cursor.getString(6);
            String type=cursor.getString(7);

            arr_food.add(new Food(id,name,dif,img_path,desc,link,note,type));
        }
        adapter.updateArrMain(arr_food);
        adapter.notifyDataSetChanged();
        cursor.close();
    }
    public static void getDataByTagAllSpinner(DatabaseSQLite databaseSQLite,Adapter adapter, List<Food> arr_food){
        Cursor cursor;
        arr_food.clear();
        cursor=databaseSQLite.getCursorData("SELECT * FROM FOOD;");
        while (cursor.moveToNext())
        {
            String id=cursor.getString(0);
            String name=cursor.getString(1);
            Double dif=cursor.getDouble(2);
            String img_path=cursor.getString(3);
            String desc=cursor.getString(4);
            String link=cursor.getString(5);
            String note=cursor.getString(6);
            String type=cursor.getString(7);

            arr_food.add(new Food(id,name,dif,img_path,desc,link,note,type));
        }
        adapter.updateArrMain(arr_food);
        adapter.notifyDataSetChanged();
        cursor.close();
    }

    // An hien mat khau
    public static void hide_appear_eye(ImageView eyeIcon, EditText etPassword) {
        eyeIcon.setOnClickListener(v -> {
            int inputType = etPassword.getInputType();

            boolean isVisible = (inputType == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD));
            // Tra ve gia tri isVisiable

            if (isVisible) {
                // Chuyển sang ẩn mật khẩu
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                eyeIcon.setImageResource(R.drawable.baseline_visibility_off_24);
            } else {
                // Chuyển sang hiện mật khẩu
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                eyeIcon.setImageResource(R.drawable.baseline_visibility_24);
            }

            // Giữ con trỏ ở cuối văn bản
            etPassword.setSelection(etPassword.getText().length());
        });
    }

    // Kiem tra dang nhap hay ko
    public static void checkedLogin(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("login_pref", Context.MODE_PRIVATE);
        boolean isLoggedIn = prefs.getBoolean("tickLogIn", false);

        if (!isLoggedIn) {
            Intent intent = new Intent(activity, NotLogin.class);
            activity.startActivity(intent);
            activity.finish();
        }
    }

    // Tao ma otp ngau nhien
    public static String createRandomOTP(){
        String chars="abcdefghijklmnopqrstuvwxyz0123456789"; // tong cong 36 ky tu so va chu
        StringBuilder otp_code=new StringBuilder();
        Random rand=new Random();
        for(int i=0;i<6;i++){
            otp_code.append(chars.charAt(rand.nextInt(chars.length())));
            // rand.nextInt => lay so ngau nhien trong khoang nay (36) -> tu 0-35
        }
        return  otp_code.toString();
    }


}
