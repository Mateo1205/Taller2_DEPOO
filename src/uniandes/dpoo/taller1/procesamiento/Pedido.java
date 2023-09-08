package uniandes.dpoo.taller1.procesamiento;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Pedido {
	
	private int idPedidos;
    private int numPedidos;
	private String nombreCliente;
	private String direccionClientes;
	private ArrayList<Producto> listaComida;
	
	public Pedido(String nombreCliente, String direccionClientes)
	{
		Random ram = new Random();
		this.idPedidos = ram.nextInt(99999);
		this.nombreCliente = nombreCliente;
		this.direccionClientes = direccionClientes;
		this.numPedidos = 0;
		this.listaComida= new ArrayList<>();	
	}
	
	public String getNombre()
	{
		return nombreCliente;
	}
	
	public ArrayList<Producto> getproductos()
	{
		return listaComida;
	}
	
	public int getIdentificador() 
	{
		return idPedidos;
	}
	
	public int getPrecioNeto()
	{
		int total = 0;
		for(int i = listaComida.size()-1; i >= 0; i--)
		{
			total = total + listaComida.get(i).getPrecio();
		}
		return total;
	}
	
	public void agregarProducto(Producto producto) 
	{
		listaComida.add(producto);
		this.numPedidos = this.numPedidos + 1;
		
	}
	
    public int getIva() 
    {
    	int total = (int) (getPrecioNeto() * 0.19);
		return total;
	}
    
    public void GuardarFactura() throws IOException
	{
		String ruta = "data/facturas/"+this.idPedidos+".txt";
		String estructuraTexto= generarTextoEnFactura();
		File file = new File(ruta);
		
		if(!file.exists()) 
		{
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(estructuraTexto);
		bw.close();
		
	}
    
	public String generarTextoEnFactura() 
	{
		String textoEstructurado = "";
		for(int i = listaComida.size()-1; i >= 0; i--)
		{
			textoEstructurado = textoEstructurado + listaComida.get(i).generarTextoFactura();
		}
		
		String textoGeneral = "HAMBURGUESA EL CORRAL\n"+
		                       "\nCliente: "+ this.nombreCliente+
		                       "\nDireccion: " + this.direccionClientes+
		                       "\nID pedido: "+this.idPedidos+
		                       "\nNumero de Pedido: "+this.numPedidos+
		                         
		                       "\n\nProductos:\n"+ textoEstructurado+
		                       
		                       "\n\nPrecion Neto:"+this.getPrecioNeto()+"\n"+
		                       "Precio IVA (19%):"+this.getIva()+"\n"+
		                       "Precio Total: "+ (this.getIva()+this.getPrecioNeto());
		 return textoGeneral;                      
	}

	

}
