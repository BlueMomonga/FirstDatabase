package ed.self.firstdatabase;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
/*
参考にしたサイト
http://www.sakc.jp/blog/archives/21645
 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ここから追加
        MyOpenHelper helper = new MyOpenHelper(this);
        // ? final とは？
        final SQLiteDatabase db = helper.getWritableDatabase();

        final EditText nameText = (EditText) findViewById(R.id.editName);
        final EditText ageText = (EditText) findViewById(R.id.editAge);

        Button entryButton = (Button) findViewById(R.id.insert);
        entryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                String age = ageText.getText().toString();

                // ? ContentValues 型とは？
                ContentValues insertValues = new ContentValues();
                insertValues.put("name", name);
                insertValues.put("age",age);
                long id = db.insert("person", name, insertValues);


            }
        });

        Button updateButton = (Button) findViewById(R.id.update);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                String age = ageText.getText().toString();

                if (name.equals("")) {
                    // ? What's Toast ?
                    Toast.makeText(MainActivity.this, "Please input name", Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues updateValues = new ContentValues();
                    updateValues.put("age",age);
                    db.update("person", updateValues, "naame=?", new String[] { name });
                }
            }
        });

        Button deleteButton = (Button) findViewById(R.id.delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                String age = ageText.getText().toString();

                if (name.equals("")) {
                    Toast.makeText(MainActivity.this, "Please input name", Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues updateValues = new ContentValues();
                    updateValues.put("age",age);
                    db.delete("person", "naame=?", new String[] { name });
                }
            }
        });

        Button deleteAllButton = (Button) findViewById(R.id.deleteALL);
        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                String age = ageText.getText().toString();

                db.delete("person",null,null);

            }
        });

        Button dataBaseButton = (Button) findViewById(R.id.showDataBase);
        dataBaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dbIntent = new Intent(
                        MainActivity.this, ShowDataBase.class);
                startActivity(dbIntent);
            }
        });
    }
}
