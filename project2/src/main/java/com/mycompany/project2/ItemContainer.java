/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project2;

import java.util.List;

/**
 *
 * @author vyshn
 */
public class ItemContainer {
    public String name;
    public int price;
    public int x_coordinate;
    public int y_coordinate;
    public int length;
    public int width;
    public int height;
    public List<Item> collection;
    
    public ItemContainer(String a,int b, int c,int d,int e,int f,int g, List<Item> h)
    {
      this.name=a;
      this.price=b;
      this.x_coordinate=c;
      this.y_coordinate=d;
      this.length=e;
      this.width=f;
      this.height=g;
      this.collection=h;
    }
   public String getName(){return this.name;}
    public int getPrice(){return this.price;}
    public int getx(){return this.x_coordinate;}
    public int gety(){return this.y_coordinate;}
    public int getLength(){return this.length;}
    public int getWidth(){return this.width;}
    public int getHeight(){return this.height;}
    public List<Item> getItemCollection()
    {
       return this.collection;
    }
    
    public void setName(String a){this.name=a;}
    public void setPrice(int a){this.price=a;}
    public void setx(int a){this.x_coordinate=a;}
    public void sety(int a){this.y_coordinate=a;}
    public void setLength(int a){this.length=a;}
    public void setWidth(int a){this.width=a;}
    public void setHeight(int a){this.height=a;}
    
}
