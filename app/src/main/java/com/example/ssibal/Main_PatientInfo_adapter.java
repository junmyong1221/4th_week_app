package com.example.ssibal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class Main_PatientInfo_adapter extends BaseAdapter {
    private ArrayList<Info_Patient> listViewItemList = new ArrayList<Info_Patient>();

    public Main_PatientInfo_adapter(){
    }

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.main_patientinfo_listitem, parent, false);
        }

        ImageView iconImageView = (ImageView) convertView.findViewById(R.id.iconitem);
        TextView patientRelationship = (TextView) convertView.findViewById(R.id.dataItem01);
        TextView patientName = (TextView) convertView.findViewById(R.id.dataItem02);
        TextView patientAge = (TextView) convertView.findViewById(R.id.dataItem03);

        final Info_Patient listViewItem = listViewItemList.get(position);

        iconImageView.setImageBitmap(listViewItem.getIcon());
        patientRelationship.setText(listViewItem.getrelationship());
        patientName.setText(listViewItem.getname());
        patientAge.setText(listViewItem.getage() + "세 "+listViewItem.getgender());


        //상세정보버튼 클릭시
        Button specificbutton = (Button)convertView.findViewById(R.id.specificbutton);
        specificbutton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //해당 리스트 클릭 시 이벤트
                Intent specificintent = new Intent(context, Main_SpecificPatientInfo.class);


                specificintent.putExtra("face", listViewItem.getIcon());
                specificintent.putExtra("name", listViewItem.getname());
                specificintent.putExtra("relationship", listViewItem.getrelationship());
                specificintent.putExtra("phonenumber", listViewItem.getphonenumber());
                specificintent.putExtra("age", listViewItem.getage());
                specificintent.putExtra("gender", listViewItem.getgender());
                specificintent.putExtra("NOKid", listViewItem.getNOKid());
                ((MainActivity)context).startActivity(specificintent);

            }
        });


        return convertView;
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    public Object getItem(int position){
        return listViewItemList.get(position);
    }

    public void addItem(Bitmap icon, String relationship, String phonenumber, String name, String age, String gender, String NOKid){
        Info_Patient item = new Info_Patient();

        item.setIcon(icon);
        item.setrelationship(relationship);
        item.setname(name);
        item.setage(age);
        item.setphonenumber(phonenumber);
        item.setgender(gender);
        item.setNOKid(NOKid);
        listViewItemList.add(item);
    }
}
