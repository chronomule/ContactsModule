package com.example.searchbar;



import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

@SuppressWarnings("deprecation")
public class ListViewSearchExample extends Activity
{
private ListView lv;
private EditText et;
private ArrayList<String> myArr = new ArrayList<String>();


private ArrayList<String> array_sort= new ArrayList<String>();
int textlength=0;

public void onCreate(Bundle savedInstanceState)
{
super.onCreate(savedInstanceState);
setContentView(R.layout.main);
Cursor cursor = getContentResolver().query(People.CONTENT_URI,
		null, null, null, null);
startManagingCursor(cursor);
int nameIdx = cursor.getColumnIndexOrThrow(People.NAME);
@SuppressWarnings("unused")
String[] result = new String[cursor.getCount()];
if (cursor.moveToFirst())
do {
	
	String name = cursor.getString(nameIdx);
    myArr.add(name);
} while(cursor.moveToNext());


lv = (ListView) findViewById(R.id.ListView01);
et = (EditText) findViewById(R.id.EditText01);
lv.setAdapter(new ArrayAdapter<String>(this,
android.R.layout.simple_list_item_multiple_choice, myArr));

lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

et.addTextChangedListener(new TextWatcher()
{
public void afterTextChanged(Editable s)
{
                                                                // Abstract Method of TextWatcher Interface.
}
public void beforeTextChanged(CharSequence s,
int start, int count, int after)
{
// Abstract Method of TextWatcher Interface.
}
public void onTextChanged(CharSequence s,
int start, int before, int count)
{
textlength = et.getText().length();
array_sort.clear();
for (int i = 0; i < myArr.size(); i++)
{
		
if (textlength <= (myArr.get(i)).length())
{
if(et.getText().toString().equalsIgnoreCase(
(String)
myArr.get(i).subSequence(0,
textlength)))
{
                                                                                                                array_sort.add(myArr.get(i));
                                                                                                }
                                                                                }
                                                                }
lv.setAdapter(new ArrayAdapter<String>
(ListViewSearchExample.this,
android.R.layout.simple_list_item_multiple_choice, array_sort));
}
});
lv.setFastScrollEnabled(true);
}
}


