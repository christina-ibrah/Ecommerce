package com.example.onlineshopping;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.OptionalLong;

public  class CustomerDatabase extends SQLiteOpenHelper {
    private static String databaseName="Online";
    SQLiteDatabase Online;
    public CustomerDatabase(Context context)
    {
        super(context,databaseName,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
    db.execSQL("create table Customer(ID integer primary key autoincrement ,username text not null ,name text not null,pass text not null," +
                "email text not null,phone text not null,gender text not null,job text not null,day text not null,month text not null,year text not null)");

    db.execSQL("create table Orders(ORDID integer primary key autoincrement,Orddate text not null,Address text not null,CustID integer, foreign KEY(CustID)references customer(ID))");

    db.execSQL("create table OrderDetails(Quantity text not null,Ord_ID integer,Pro_ID integer,foreign KEY(Ord_ID)references Orders(ORDID),foreign KEY(Pro_ID)references Orders(ProID),Primary key(Ord_ID,Pro_ID))");

    db.execSQL("create table categorie(CatID integer primary key autoincrement ,CatName text not null)");

    db.execSQL("create table product(ProID integer primary key autoincrement ,ProName text not null " +
            ",Price text not null ,Quantity text not null ,catID integer,foreign KEY(catID)references Categories(CatID))");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop table if exists Customer");
        db.execSQL("drop table if exists Orders");
        db.execSQL("drop table if exists OrderDetails");
        db.execSQL("drop table if exists categorie");
        db.execSQL("drop table if exists product");
        onCreate(db);
    }
    public int Getorderid()
    {
        Online=getReadableDatabase();
        Cursor c = Online.rawQuery("select * from product", null);
        if(c!=null)
            c.moveToFirst();
        Online.close();
        return c.getCount();
    }
    public Cursor GetProductID(String name)
    {
        Online=getReadableDatabase();
        String[] Name = {name};
        Cursor c = Online.rawQuery("select ProID from product where ProName LIKE ?", Name);
        if(c!=null)
            c.moveToFirst();
        Online.close();
        return c;
    }
    public void addorderdetails(String Q,int ordid,int proid)
    {
        ContentValues row=new ContentValues();
        row.put("Quantity",Q);
        row.put("Ord_ID",ordid);
        row.put("Pro_ID",proid);
        Online=getWritableDatabase();
        Online.insert("OrderDetails", null, row);
        Online.close();
    }
    public void addorder(String date,String address,int id)
    {
        ContentValues row=new ContentValues();
        row.put("Orddate",date);
        row.put("Address",address);
        row.put("CustID",id);
        Online=getWritableDatabase();
        Online.insert("Orders", null, row);
        Online.close();
    }

    public void createNewCustomer(String username,String name, String pass, String email, String phone, String gender,String job,String day,String month,String year){
        ContentValues row = new ContentValues();
        row.put("username", username);
        row.put("name", name);
        row.put("pass",pass);
        row.put("email", email);
        row.put("phone", phone);
        row.put("gender",gender);
        row.put("job", job);
        row.put("day",day);
        row.put("month", month);
        row.put("year",year);
        Online=getWritableDatabase();
        Online.insert("Customer", null, row);
        Online.close();
    }
    public void createcat()
    {
        ContentValues row = new ContentValues();
        row.put("CatName", "sport");
        Online=getWritableDatabase();
        Online.insert("categorie", null, row);
        row.put("CatName", "clothes");
        Online=getWritableDatabase();
        Online.insert("categorie", null, row);
        Online.close();
        row.put("CatName", "food");
        Online=getWritableDatabase();
        Online.insert("categorie", null, row);
        Online.close();
    }
    public void createpro()
    {

        ContentValues row = new ContentValues();
        row.put("ProName", "ball");
        row.put("Price", "50");
        row.put("Quantity", "50");
        row.put("catID",1);
        Online=getWritableDatabase();
        Online.insert("product", null, row);
        Online.close();
        row.put("ProName", "Sport Shoes");
        row.put("Price", "150");
        row.put("Quantity", "50");
        row.put("catID",1);
        Online=getWritableDatabase();
        Online.insert("product", null, row);
        Online.close();
        row.put("ProName", "Dress");
        row.put("Price", "500");
        row.put("Quantity","100");
        row.put("catID",2);
        Online=getWritableDatabase();
        Online.insert("product", null, row);
        Online.close();
        row.put("ProName", "Skirt");
        row.put("Price", "200");
        row.put("Quantity","100");
        row.put("catID",2);
        Online=getWritableDatabase();
        Online.insert("product", null, row);
        Online.close();
        row.put("ProName", "egg");
        row.put("Price", "5");
        row.put("Quantity","100");
        row.put("catID",3);
        Online=getWritableDatabase();
        Online.insert("product", null, row);
        Online.close();
        row.put("ProName", "milk");
        row.put("Price", "5");
        row.put("Quantity","100");
        row.put("catID",3);
        Online=getWritableDatabase();
        Online.insert("product", null, row);
        Online.close();
    }
    public Cursor GetIdCat(String catogory)
    {
        Online=getReadableDatabase();
        String[] args = {catogory};
        Cursor c=Online.rawQuery("select CatID from categorie where CatName=?",args);
        if(c!=null) {
            c.moveToFirst();
        }
        Online.close();
        return c;
    }
    public Cursor GetProfromCatID(String catid)
    {
        Online=getReadableDatabase();
        String[] args = {catid};
        Cursor c=Online.rawQuery("select ProName from product where catID=?",args);
        if(c!=null) {
            c.moveToFirst();
        }
        Online.close();
        return c;
    }
    public int Getnumorders()
    {
        Online=getReadableDatabase();
        Cursor c=Online.rawQuery("select * from Orders",null);
        if(c!=null) {
            c.moveToFirst();
        }
        Online.close();
        return c.getCount();
    }
    public Cursor GetCategories()
    {
        Online=getReadableDatabase();
        Cursor c = Online.rawQuery("select CatName from categorie",null);
        if(c!=null)
            c.moveToFirst();
        Online.close();
        return c;
    }
    public Cursor GetProducts()
    {
        Online=getReadableDatabase();
        Cursor c = Online.rawQuery("select ProName from product", null);
        if(c!=null)
            c.moveToFirst();
        Online.close();
        return c;
    }
    public Cursor Getproductprice(String name)
    {
        Online=getReadableDatabase();
        String[] Name = {name};
        Cursor c = Online.rawQuery("select Price from product where ProName LIKE ?", Name);
        if(c!=null)
            c.moveToFirst();
        else
            return  null;
        Online.close();
        return c;
    }
    public Cursor Getproductqua(String name)
    {
        Online=getReadableDatabase();
        String[] Name = {name};
        Cursor c = Online.rawQuery("select Quantity from product where ProName LIKE ?", Name);
        if(c!=null)
            c.moveToFirst();
        else
            return  null;
        Online.close();
        return c;
    }

    public void deletecus()
    {
        Online=this.getWritableDatabase();
        Online.delete("customer",null,null);
        Online.close();
    }
    public void deletecat()
    {
        Online=this.getWritableDatabase();
        Online.execSQL("drop table if exists categorie");
        Online.execSQL("drop table if exists product");
        Online.execSQL("drop table if exists Orders");
        Online.execSQL("drop table if exists OrderDetails");

        Online.execSQL("create table categorie(CatID integer primary key autoincrement ,CatName text not null)");
        Online.execSQL("create table product(ProID integer primary key autoincrement ,ProName text not null " +
                ",Price text not null ,Quantity text not null ,catID integer,foreign KEY(catID)references Categories(CatID))");

        Online.execSQL("create table Orders(ORDID integer primary key autoincrement,Orddate text not null,Address text not null,CustID integer, foreign KEY(CustID)references customer(ID))");

        Online.execSQL("create table OrderDetails(Quantity text not null,Ord_ID integer,Pro_ID integer,foreign KEY(Ord_ID)references Orders(ORDID),foreign KEY(Pro_ID)references Orders(ProID),Primary key(Ord_ID,Pro_ID))");

        Online.close();
    }
    public void deletepro()
    {
        Online=this.getWritableDatabase();
        Online.delete("product",null,null);
        Online.close();
    }
    public Cursor fetchpass(String name){
        Online=getReadableDatabase();
        String[] Name = {name};
        Cursor c = Online.rawQuery("select pass from Customer where username LIKE ?", Name);
        if(c!=null)
            c.moveToFirst();
        else
            return  null;
       Online.close();
        return c;
    }
    public Cursor getProducts(String name)
    {
        Online=getReadableDatabase();
        String[] n={"%"+ name.toLowerCase()+ "%" };
        Cursor cursor = Online.rawQuery("Select ProName from product where lower(ProName) like ?",n);
        cursor.moveToFirst();
        Online.close();
        return cursor;
    }
    public Cursor Foundcustomer(String name,String pass)
    {
        Online=getReadableDatabase();
        String[] args = {name,pass};
        Cursor c=Online.rawQuery("select ID from Customer where username=? and pass =?",args);
        if(c!=null)
            c.moveToFirst();
        Online.close();
        return c;
    }
    public Cursor Foundname(String username)
    {
        Online=getReadableDatabase();
        String[] args = {username};
        Cursor c=Online.rawQuery("select ID from Customer where username=?",args);
        if(c!=null)
            c.moveToFirst();
        Online.close();
        return c;
    }
    public Cursor getNames()
    {
        Online=getReadableDatabase();
        Cursor c=Online.rawQuery("select username from Customer",null);
        if(c!=null) {
            c.moveToFirst();
        }
        else
            return null;
        Online.close();
        c.close();
        return c;
    }
    public Cursor Foundemail(String username)
    {
        Online=getReadableDatabase();
        String[] args = {username};
        Cursor c=Online.rawQuery("select email from Customer where username like?",args);
        if(c!=null)
            c.moveToFirst();
        Online.close();
        return c;
    }

    public Cursor GetCusinfo (String user)
    {
        Online = getReadableDatabase();
        String[] args = {user};
        String query = " select * from Customer where username Like ?" ;
        Cursor cu = Online.rawQuery(query, args);
        cu.moveToFirst();
        cu.close();
        return cu;
    }
    public void ConfirmPassword(String user, String newpass)
    {
        /*Online = getWritableDatabase();
        Cursor c= GetCusinfo (user);
        ContentValues row = new ContentValues();
        row.put("username", c.getColumnIndex("username"));
        row.put("name", c.getColumnIndex("name"));
        row.put("pass",newpass);
        row.put("email", c.getColumnIndex("email"));
        row.put("phone", c.getColumnIndex("phone"));
        row.put("gender",c.getColumnIndex("gender"));
        row.put("job", c.getColumnIndex("job"));
        row.put("day",c.getColumnIndex("day"));
        row.put("month", c.getColumnIndex("month"));
        row.put("year",c.getColumnIndex("year"));
        Online.update("Customer",row,"username like ?",new String[]{user});
        Online.close();*/
        Online=getWritableDatabase();
        Online.execSQL("UPDATE Customer SET pass="+"'"+newpass+"'"+"WHERE username ="+"'"+user+"'");
        Cursor c=GetCusinfo(user);
        int n=c.getColumnIndex("pass");
        Log.d("pass",String.valueOf(n));

        Online.close();
    }


}
