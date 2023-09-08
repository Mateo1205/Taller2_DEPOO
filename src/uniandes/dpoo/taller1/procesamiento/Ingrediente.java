package uniandes.dpoo.taller1.procesamiento;

public class Ingrediente 
{
	
	private String nombre;
	private int costoAdicional;
	private int codigo;
	
	public Ingrediente(String nombre, int costoAdicional, int codigo)
	{
		this.codigo = codigo;
		this.nombre = nombre;
		this.costoAdicional = costoAdicional;
	}
	
    public int getCodigo() 
	{
		return codigo;
	}
    
	public String getNombre() 
	{
		return nombre;
	}
	
	public int getPrecio() 
	{
		return costoAdicional;
	}
	
}
