package tadakazu1972.fireemergency;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

/**
 * Created by tadakazu on 2016/10/08.
 */

public class CustomCursorAdapter extends SimpleCursorAdapter {
    private Context context;
    private Cursor c;
    protected ArrayList<Boolean> itemChecked = new ArrayList<Boolean>();

    public CustomCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags){
        super(context, layout, c, from, to, flags);
        this.context = context;
        this.c = c;
        //データ数に応じてチェック保存用リスト初期化
        for (int i=0 ;i < this.getCount(); i++){
            itemChecked.add(i, false); // initializes all items value
        }
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent){
        View view = super.newView(context, cursor, parent);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor){
        super.bindView(view, context, cursor);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        View view = super.getView(position, convertView, parent);

        /*if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.record_view_delete, null);
        }*/

        CheckBox cBox = (CheckBox)view.findViewById(R.id.checkbox);
        cBox.setOnClickListener(new OnClickListener(){
            public void onClick(View v){
                CheckBox cb = (CheckBox)v.findViewById(R.id.checkbox);
                if (cb.isChecked()){
                    itemChecked.set(position, true);
                } else if (!cb.isChecked()){
                    itemChecked.set(position, false);
                }
            }
        });
        cBox.setChecked(itemChecked.get(position));
        cBox.setFocusable(false); //これしないとリストの他要素がタップきかない

        return view;
    }

    public void clickData(final int position, View view){
        //次の行はデバッグ用
        //Toast.makeText(context, "タップしたposition:"+String.valueOf(position), Toast.LENGTH_SHORT).show();

        //以下のコードは上のgetViewとロジックが逆であることに注意。DataActivityのListViewのタップによって呼ばれるから逆になる。よく考えればわかる。
        CheckBox cBox = (CheckBox)view.findViewById(R.id.checkbox);
        if (cBox.isChecked()){
            itemChecked.set(position, false);
        } else if (!cBox.isChecked()){
            itemChecked.set(position, true);
        }
        cBox.setChecked(itemChecked.get(position));
        cBox.setFocusable(false); //これしないとリストの他要素がタップきかないBoolean
    }
}
