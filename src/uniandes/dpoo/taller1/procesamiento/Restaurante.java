package uniandes.dpoo.taller1.procesamiento;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class Restaurante {
	
	private int numPedidos;
	private Map<Integer,Pedido> mapPedido;
	private Pedido pedidoEnCurso;
	private ProductoAjustado pedidoAjustado;
	
	
	private ArrayList<Ingrediente> ingredientsLista;
	private ArrayList<ProductoMenu> menuLista;
	private ArrayList<Combo> combosLista;
	private ArrayList<Bebidas> listaBebidas;
	
	public Restaurante() 
	{
		this.numPedidos = 0;
		ProductoMenu menu = new ProductoMenu(null,0,0);
		this.pedidoAjustado = new ProductoAjustado(menu);
		this.mapPedido = new HashMap<>();
		
	}

	private void loadIngredients(String archivoIngredientes)
	{
		FileReader archivo;
		BufferedReader lector;
		try
		{
			archivo = new FileReader(archivoIngredientes);
			lector = new BufferedReader(archivo);
			String cadena;
			int codigo = 100;
			java.util.ArrayList<Ingrediente> ingredients = new java.util.ArrayList<>();
			while((cadena = lector.readLine()) != null)
			{
				String[ ] partes = cadena.split(";");
				String ingredent = partes[0];
				int precio = Integer.parseInt(partes[1]);
				
				
				Ingrediente objIngre = new Ingrediente(ingredent,precio,codigo);
				codigo = codigo + 1;
				ingredients.add(objIngre);
			}
		    ingredientsLista = ingredients;
		
		}
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR: el archivo indicado no se encontró.");
		}
		catch (IOException e)
		{
			System.out.println("ERROR: hubo un problema leyendo el archivo.");
			System.out.println(e.getMessage());
		}
	}
	
	private void loadMenu(String archivoMenu)
	{
		
		FileReader archivo;
		BufferedReader lector;
		try
		{
			archivo = new FileReader(archivoMenu);
			lector = new BufferedReader(archivo);
			String cadena;
			int codigo = 200;
			java.util.ArrayList<ProductoMenu> menu = new java.util.ArrayList<>();
			while((cadena = lector.readLine()) != null)
			{
				String[ ] partes = cadena.split(";");
				String comida = partes[0];
				int precio = Integer.parseInt(partes[1]);
				
				ProductoMenu objMenu = new ProductoMenu(comida,precio,codigo);
				codigo = codigo + 1;
				menu.add(objMenu);		
			}
			menuLista = menu;
		
		}
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR: el archivo indicado no se encontró.");
		}
		catch (IOException e)
		{
			System.out.println("ERROR: hubo un problema leyendo el archivo.");
			System.out.println(e.getMessage());
		}
	}
	
	private void loadCombo(String archivoCombo, ArrayList<ProductoMenu> productos )
	{
		
		FileReader archivo;
		BufferedReader lector;
		try
		{
			archivo = new FileReader(archivoCombo);
			lector = new BufferedReader(archivo);
			String cadena;
			int codigo = 300;
			java.util.ArrayList<Combo> combos = new java.util.ArrayList<>();
		
			while((cadena = lector.readLine()) != null)
			{
				String[ ] partes = cadena.split(";");
				String comida = partes[0];
				String descuento = partes[1];
				descuento = descuento.replace("%","");
				int descuento1 = Integer.parseInt(descuento);
				String burger = partes[2];
				String papas = partes[3];
				String bebida = partes[4];
				
				ProductoMenu hamburguer = null;
				ProductoMenu fries = null;
				ProductoMenu drink = null;
				
				ArrayList<ProductoMenu> listaProductos = new ArrayList<>();
				
				for(int i = productos.size()-1; i>= 0;i--) 
				{
					ProductoMenu opcion = productos.get(i);
				
					if(opcion.getNombre().equals(burger))
					{
						hamburguer = opcion;
					}
						
					else if (opcion.getNombre().equals(papas))
					{
						fries = opcion;
					}
					else if( opcion.getNombre().equals(bebida))
					{
						drink = opcion;
					}
				}
				
				Combo ComboAct = null;
				ComboAct = new Combo(comida,descuento1,codigo,listaProductos);
			    codigo = codigo + 1;
			    combos.add(ComboAct);
			    
			    ComboAct.agregarItemCombo(hamburguer);
			    ComboAct.agregarItemCombo(fries);
				ComboAct.agregarItemCombo(drink);
				
			}
			combosLista = combos;  
		}
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR: el archivo indicado no se encontró.");
		}
		catch (IOException e)
		{
			System.out.println("ERROR: hubo un problema leyendo el archivo.");
			System.out.println(e.getMessage());
		}	
	}
	
	private void loadBedidas(String nombreBebidas) {
	
			FileReader archivo;
			BufferedReader lector;
			try
			{
				archivo = new FileReader(nombreBebidas);
				lector = new BufferedReader(archivo);
				String cadena;
				int codigo = 500;
				java.util.ArrayList<Bebidas> drink = new java.util.ArrayList<>();
				while((cadena = lector.readLine()) != null)
				{
					String[ ] partes = cadena.split(";");
					String bebida = partes[0];
					int precio = Integer.parseInt(partes[1]);
					
					Bebidas objMenu = new Bebidas(bebida,precio,codigo);
					codigo = codigo + 1;
					drink.add(objMenu);		
				}
				listaBebidas = drink;
			}
			catch (FileNotFoundException e)
			{
				System.out.println("ERROR: el archivo indicado no se encontró.");
			}
			catch (IOException e)
			{
				System.out.println("ERROR: hubo un problema leyendo el archivo.");
				System.out.println(e.getMessage());
			}
			
		}
	
	
	public void cargarInformacionRestaurante(String archivoMenu,String nombreCombo,String nombreIngredientes,String nombreBebidas) 
	{
	     loadIngredients(nombreIngredientes);
	     loadMenu(archivoMenu);
	     loadBedidas(nombreBebidas);
	     loadCombo(nombreCombo,menuLista);  		 
	}


	public void menuPantalla(){
		System.out.println("\n************************************MENU******************************************");
		for(int i = menuLista.size() -1; i >=0 ; i--)
		{
			ProductoMenu opc = menuLista.get(i);
			System.out.println(opc.getCodigo() +" = "+ opc.getNombre()+" X "+ opc.getPrecio());
		}
		System.out.println("\n************************************COMBOS******************************************");
		for(int i = combosLista.size()-1;i >= 0;i--)
		{
			Combo opc1 = combosLista.get(i);
			ArrayList<ProductoMenu> listaPro = opc1.getproductos();
			ProductoMenu burger = listaPro.get(0);
			ProductoMenu fries = listaPro.get(1);
			ProductoMenu drink = listaPro.get(2);
		    System.out.println(opc1.getCodigo() +" = "+ opc1.getNombre()+" X "+ opc1.getDescuento()+"("+burger.getNombre()+","+fries.getNombre()+","+drink.getNombre()+")");
			
		}
		System.out.println("\n************************************INGREDIENTES******************************************");
		for(int i = ingredientsLista.size() -1; i >=0 ; i--)
		{
			Ingrediente opc2 = ingredientsLista.get(i);
			System.out.println(opc2.getCodigo() +" = "+ opc2.getNombre()+" X "+ opc2.getPrecio());
		}
		
		System.out.println("\n************************************BEBIDAS******************************************");
		for(int i = listaBebidas.size() -1; i >=0 ; i--)
		{
			Bebidas opc3 = listaBebidas.get(i);
			System.out.println(opc3.getCodigo() +" = "+ opc3.getNombre()+" X "+ opc3.getPrecio());
		}
		
	}
	
	public void mostrarProductos()
	{
		ArrayList<Producto> hh =pedidoEnCurso.getproductos();
		for(int i = hh.size() -1; i >=0 ; i--)
		{
			System.out.println("   -"+hh.get(i).getNombre());
		}
		
	}


	public void iniciarPedido(String nombre, String direccion) 
	{
		this.numPedidos = this.numPedidos+1;
		pedidoEnCurso = new Pedido(nombre,direccion);	
	}
	
	public boolean buscarIgualPedido() {
	    for (Entry<Integer, Pedido> entry : mapPedido.entrySet()) {
	        Pedido value = entry.getValue();
	        ArrayList<Producto> lista = value.getproductos();

	        if (lista.size() == pedidoEnCurso.getproductos().size()) {
	            boolean iguales = true;
	            for (Producto producto : lista) {
	                if (!pedidoEnCurso.getproductos().contains(producto)) {
	                    iguales = false;
	                    break; // Si un producto no está en la lista, podemos salir del bucle
	                }
	            }

	            if (iguales) {
	                return true; // Las listas son iguales
	            }
	        }
	    }
	    return false; // No se encontró una lista igual
	}


	public void mostrarId() {
		System.out.println("\n********************************** ID DE LOS PEDIDOS**********************************");
		for (Entry<Integer, Pedido> entry : mapPedido.entrySet()) {
            Integer key = entry.getKey();
            
            System.out.println("     -"+key);
		}
		
	}
	public void buscarPedidoID(String codigoId) {
		String factura = null;
		Integer idBuscado = Integer.parseInt(codigoId);
		for (Entry<Integer, Pedido> entry : mapPedido.entrySet()) {
            Integer key = entry.getKey();
            Pedido value = entry.getValue();
            if(idBuscado.equals(key))
            {
              factura = value.generarTextoEnFactura();
            }
        }
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~FACTURA~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\n"+factura);
	}




	
	
	
	public void cerrarYGuardarPedido() {
		try {
			pedidoEnCurso.GuardarFactura();
			this.mapPedido.put(pedidoEnCurso.getIdentificador(), pedidoEnCurso);
			pedidoEnCurso = null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void agregarProductoMenu(int codigoProduct) 
	{
		
  		
        Producto proMenu = null;
    	
    	if(codigoProduct >=200 && codigoProduct <=250)
    	{
    		proMenu = buscarProductoMenu(codigoProduct);
    	}
    	else if(codigoProduct >=500 && codigoProduct <=502)
    	{
    		proMenu = buscarProductoBebida(codigoProduct);
    	}
    	

		if ((pedidoAjustado.getNombre() != null )&&(pedidoAjustado.getNombre().equals(proMenu.getNombre())))
    	{
    	     pedidoEnCurso.agregarProducto(pedidoAjustado);
    	}
		else 
		{
			pedidoEnCurso.agregarProducto(proMenu);
		}
		ProductoMenu menu = new ProductoMenu(null,0,0);
		this.pedidoAjustado = new ProductoAjustado(menu);
		
	}
	
	public void agregarProductoCombo(int codigoProducto) 
	{
		Combo proCombo = buscarXCombo(codigoProducto);
		pedidoEnCurso.agregarProducto(proCombo);
	}
	
    public void ProductoAjustadoDiseñado(int codigoProduct, int elementoIngre,int conEliAgre) 
    {
    	Producto productoBase = null;
    	if(codigoProduct >=200 && codigoProduct <=250)
    	{
    		productoBase = buscarProductoMenu(codigoProduct);
    	}
    	else if(codigoProduct >=500 && codigoProduct <=502)
    	{
    		productoBase = buscarProductoBebida(codigoProduct);
    	}
    	
    	
    	if (pedidoAjustado.getNombre() != productoBase.getNombre())
    	{
			pedidoAjustado = new ProductoAjustado(productoBase);
    	}
    	Ingrediente elementoIngrediente = buscarXIngrediente(elementoIngre);
    	
    	
    	if(conEliAgre == 1) 
    	{
    		pedidoAjustado.agregarIngrediente(elementoIngrediente);
    	}
    	else if (conEliAgre == 2)
    	{
    		pedidoAjustado.eliminarIngrediente(elementoIngrediente);
    	}
    	
		
	}
    
	public ProductoMenu buscarProductoMenu(int codigoProducto) 
	{
	   ProductoMenu tem = null;
	   
		for(int i = menuLista.size() -1; i >=0 ; i--)
		{
			ProductoMenu opc = menuLista.get(i);
			int cod =opc.getCodigo();
			if (cod == codigoProducto)
			{
				 tem = opc;
				 return tem;
			}
		}
		return tem;
	}
	
	public Bebidas buscarProductoBebida(int codigoProducto) 
	{
	   Bebidas tem = null;
	   
		for(int i = listaBebidas.size() -1; i >=0 ; i--)
		{
			
			Bebidas opc = listaBebidas.get(i);
			int cod =opc.getCodigo();
			if (cod == codigoProducto)
			{
				 tem = opc;
				 return tem;
			}
		}
		return tem;
	}
	
	public Combo buscarXCombo(int codigoProducto)
	{
	    Combo temp = null;
		for(int i = combosLista.size() -1; i >=0 ; i--)
		{
			Combo opc = combosLista.get(i);
			int cod =opc.getCodigo();
			if (cod == codigoProducto)
			{
				temp = opc;
				return temp;
			}
		}
		return temp;
	}
	
	
	public Ingrediente buscarXIngrediente(int codigoProducto) 
	{
		Ingrediente temp = null;
		for(int i = ingredientsLista.size() -1; i >=0 ; i--)
		{
			Ingrediente opc = ingredientsLista.get(i);
			int cod =opc.getCodigo();
			if (cod == codigoProducto) 
			{
				temp = opc;
				return temp;
			}
		}
		return temp;
	}

	

	

	

}
