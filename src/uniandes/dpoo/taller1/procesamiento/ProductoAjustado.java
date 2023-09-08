package uniandes.dpoo.taller1.procesamiento;

import java.util.ArrayList;

public class ProductoAjustado implements Producto {

    private Producto productoBase;
    private ArrayList<Ingrediente> agregarProducto;
    private ArrayList<Ingrediente> eliminarProducto;
	
	public ProductoAjustado(Producto productoBase){
		this.productoBase = productoBase;
		this.agregarProducto = new ArrayList<>();
		this.eliminarProducto = new ArrayList<>();
		
	}
	
	public int getPrecio() 
	{
		int precio = 0;
		for(int i = agregarProducto.size()-1; i>=0; i--)
		{
			precio = precio + agregarProducto.get(i).getPrecio();
		}
		return precio + productoBase.getPrecio();
	}

	
	public String getNombre() 
	{   
			return productoBase.getNombre();
		
		
		
	}
	
	public void agregarIngrediente(Ingrediente ingre)
	{
		agregarProducto.add(ingre);
	}
	
	public void eliminarIngrediente(Ingrediente ingre)
	{
		eliminarProducto.add(ingre);
	}
    
	public String generarTextoFactura() 
	{
		String texto = "";
		texto = texto + "\n            *"+this.getNombre()+"       "+this.getPrecio()+"\n";
		
		for(int i = agregarProducto.size()-1; i>=0; i--)
		{
			texto = texto + "                +"+ agregarProducto.get(i).getNombre()+"   "+(agregarProducto.get(i).getPrecio())+"\n";
		}
		for(int i = eliminarProducto.size()-1; i>=0; i--)
		{
			texto = texto + "                -"+ eliminarProducto.get(i).getNombre()+"\n";
		}  
		return texto;
	}

}
