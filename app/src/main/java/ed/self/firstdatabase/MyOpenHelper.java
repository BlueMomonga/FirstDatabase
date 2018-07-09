package ed.self.firstdatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Akiko Mikami on 2018/04/21.
 */

public class MyOpenHelper extends SQLiteOpenHelper {
    public MyOpenHelper(Context context){
        super(context, "NameAgeDB", null,1);
    }

    @Override

    // SQLiteOpenHelper の継承を宣言したら、警告からimplement を選択→次の行が生成される
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table person ( name text not null, age text);");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
