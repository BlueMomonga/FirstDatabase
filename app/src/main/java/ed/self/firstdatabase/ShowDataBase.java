package ed.self.firstdatabase;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Akiko Mikami on 2018/04/21.
 show_database.xmlと紐づいたActivityです。
 データベースの中身を表示させるための処理を記述しています。
 */

public class ShowDataBase extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // show_database はlayout の中の show_database.xml
        setContentView(R.layout.show_database);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);

        MyOpenHelper helper = new MyOpenHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        // query 実行例
        Cursor c = db.query("person", new String[] {"name", "age"}, null, null, null,null,null ,null);
        boolean mov = c.moveToFirst();
        while (mov) {
            TextView textView = new TextView(this);
            textView.setText(String.format("%s : %d歳", c.getString(0), c.getInt(1)));
            mov=c.moveToNext();
            layout.addView(textView);
        }
        c.close();
        db.close();
    }

}
