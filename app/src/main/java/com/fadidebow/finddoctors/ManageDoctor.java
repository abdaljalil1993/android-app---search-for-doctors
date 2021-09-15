package com.fadidebow.finddoctors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ManageDoctor extends AppCompatActivity {
    String url="https://scoutofsyria.000webhostapp.com/alluser.php";
    ListView listView;
    //  myadapter mm;
    ArrayList<doctoritem> listwithdata=new ArrayList<doctoritem>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_doctor);

        listView=findViewById(R.id.mylist);
        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject js=new JSONObject(s);
                    JSONArray jr=js.getJSONArray("allstudent");
                    // JSONArray jr=new JSONArray(s);
                    for(int i=0;i<jr.length();i++)
                    {
                        JSONObject j=jr.getJSONObject(i);
                        String id=j.getString("id");
                        String name=j.getString("name");

                        String phone=j.getString("phone");
                        String address=j.getString("address");
                        String username=j.getString("username");
                        String password=j.getString("password");
                        String type=j.getString("type");




                        // String enddate=j.getString("enddate");
                        // Toast.makeText(Home.this, ""+name+" "+price, Toast.LENGTH_SHORT).show();


                        listwithdata.add(new doctoritem(id,name,phone,address,username,password,type));
                        ManageDoctor.myadapter mm=new ManageDoctor.myadapter(listwithdata);
                        //   Toast.makeText(getApplicationContext(), "hi", Toast.LENGTH_SHORT).show();
                    }
                }

                catch (JSONException e) {
                    e.printStackTrace();
                }





                ManageDoctor.myadapter mm=new ManageDoctor.myadapter(listwithdata);
                listView.setAdapter(mm);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        ManageDoctor.myadapter mm=new ManageDoctor.myadapter(listwithdata);
        listView.setAdapter(mm);
        RequestQueue re= Volley.newRequestQueue(getApplicationContext());
        re.add(request);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "please press back on screen ", Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf=getMenuInflater();
        inf.inflate(R.menu.mymenu,menu);
        MenuItem itemsearch=menu.findItem(R.id.search);
        SearchView searchView=(SearchView) itemsearch.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<doctoritem> newlist=new ArrayList<doctoritem>();//to save result after search
                for(doctoritem x : listwithdata)
                {
                    if(x.name.contains(newText.toLowerCase()))
                    {
                        newlist.add(x);
                    }

                }

                //  ArrayAdapter adapter=new ArrayAdapter(Managestudent.this,android.R.layout.simple_list_item_1,newlist);
                ManageDoctor.myadapter m1=new ManageDoctor.myadapter(newlist);

                listView.setAdapter(m1);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int x=item.getItemId();
        if(x==R.id.h)
        {
            startActivity(new Intent(ManageDoctor.this,Admin.class));
        }

        if(x==R.id.exit)
        {
            startActivity(new Intent(ManageDoctor.this,Login.class));
        }

        return super.onOptionsItemSelected(item);
    }

    class  myadapter extends BaseAdapter {
        ArrayList<doctoritem> ls=new ArrayList<doctoritem>();

        public myadapter(ArrayList<doctoritem> ls) {
            this.ls = ls;
        }

        @Override
        public int getCount() {
            return ls.size();
        }

        @Override
        public Object getItem(int position) {
            return ls.get(position).name;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View v;
            LayoutInflater inf=getLayoutInflater();
            v= inf.inflate(R.layout.listitem,null);
            TextView t=v.findViewById(R.id.name);

            t.setText(ls.get(position).name);

            TextView t1=v.findViewById(R.id.phone);

            t1.setText(ls.get(position).phone);

            TextView t2=v.findViewById(R.id.address);

            t2.setText(ls.get(position).address);
            // Picasso.with(getApplicationContext()).load("http://192.168.1.101/onlinetest2/images/"+xx).into(img);
            // img.setBackgroundResource(R.drawable.bg);
           /* img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(Home.this,Productdetails.class);
                    i.putExtra("id",ls.get(position).id);
                    i.putExtra("name",ls.get(position).name);
                    i.putExtra("image",ls.get(position).image);
                    i.putExtra("description",ls.get(position).description);
                    i.putExtra("type",ls.get(position).type);
                    i.putExtra("price",ls.get(position).price);
                    i.putExtra("qte",ls.get(position).qte);
                    i.putExtra("enddate",ls.get(position).enddate);


                    startActivity(i);
                }
            });*/
            Button b=v.findViewById(R.id.button8);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(ManageDoctor.this,Doctordetails.class);
                    i.putExtra("id",ls.get(position).id.toString());
                    i.putExtra("name",ls.get(position).name.toString());
                    i.putExtra("phone",ls.get(position).phone.toString());
                    i.putExtra("address",ls.get(position).address.toString());
                    i.putExtra("username",ls.get(position).username.toString());
                    i.putExtra("password",ls.get(position).password.toString());
                    i.putExtra("type",ls.get(position).type.toString());



                    startActivity(i);
                }
            });


            return v;
        }
    }
}
