package uniandes.dpoo.taller1.procesamiento;

public class Bebidas implements Producto{
	String nombre;
	 int precioBase;
	 int codigo;
	 
	public Bebidas(String nombre, int precioBase,int codigo)
	{
		this.nombre = nombre;
		this.precioBase = precioBase;
		this.codigo = codigo;
	}

	public int getPrecio()
	{
		return precioBase;
	}

	public String getNombre() 
	{
		return nombre;
	}
	
	public int getCodigo() 
	{
		return codigo;
	}

	public String generarTextoFactura() 
	{
		String text = "\n            *"+this.getNombre()+"            "+this.getPrecio()+"\n";
		return text;
	}

}

