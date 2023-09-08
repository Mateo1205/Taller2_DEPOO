package uniandes.dpoo.taller1.procesamiento;

import java.util.ArrayList;


public class Combo implements Producto{
	
	private String nombreCombo;
	private int codigo;
	private int descuento;
	private java.util.ArrayList<ProductoMenu> productos;

	public Combo(String nombreCombo, int descuento, int codigo, ArrayList<ProductoMenu> productos)
	{
		this.nombreCombo = nombreCombo;
		this.codigo = codigo;
		this.productos = productos;
		this.descuento = descuento;
	} 
	
	
	
	public int getCodigo() 
	{
		
		return codigo;
	}
	
	public String getNombre() 
	{
		return nombreCombo;
	}
	
	public int getDescuento()
	{
		return descuento;
	}
	
	
	
	public int getPrecio() 
	{
		int precio = 0;
		for(int i = productos.size()-1;i >=0;i--)
		{
			precio = precio + productos.get(i).precioBase;
		}
		double descuento = this.getDescuento();
		double precio1 = precio - (precio*(descuento/100));
			
		return (int) precio1;
	}

	public ArrayList<ProductoMenu> getproductos() 
	{
		return productos;
	}
	
	void agregarItemCombo(ProductoMenu producto)
	{
		productos.add(producto);
	}

	public String generarTextoFactura() 
	{
		String text = "\n            *"+this.getNombre()+"            "+this.getPrecio()+"\n ";
		return text;
	}

}
