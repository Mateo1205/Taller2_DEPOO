package uniandes.dpoo.taller1.consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import uniandes.dpoo.taller1.procesamiento.Restaurante;



public class Aplicacion {
     
	 Restaurante restaurant = new Restaurante();
	 
	 public void ejecutarOpcion()
		{
			System.out.println("Estadísticas sobre los Juegos Olímpicos\n");

			boolean continuar = true;
			mostrarMenu();
			
			while (continuar)
			{
				try
				{   
					mostrarOpciones();
					int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
					
					
					
					if (opcion_seleccionada ==1)
					{
						restaurant.menuPantalla();
					}
					else if (opcion_seleccionada==2)
					{
						String nombre = input("\nNombre");
						String direccion = input("Digite Direccion");
						restaurant.iniciarPedido(nombre,direccion);
						System.out.println("\nPEDIDO INICIADO!!!");
					}
	                else if (opcion_seleccionada==3)
						
					{
	                	
	                boolean continuarmodi = true;
	                while(continuarmodi)
	                    {
					
								int codigoProduct = Integer.parseInt(input("\nIngrese el codigo del producto (Combo o del Menu): \n"));
									  
							    if ((codigoProduct >= 200 && codigoProduct <=250) ||(codigoProduct >= 500 && codigoProduct <=502) )
									{
										 
									    boolean continuar2 = true;
									    while(continuar2)
									    {  
											int conModi = Integer.parseInt(input("\n\n¿Desea modificar algo del producto(1. Si 2.No)?\n"));
													   
										    if(conModi == 1)
											  {
											    int elementoAgregar = Integer.parseInt(input("Digite el codigo del ingrediente:\n"));
												int conEliAgre = Integer.parseInt(input("1.Agregar 2.Eliminar: \n"));
												restaurant.ProductoAjustadoDiseñado(codigoProduct,elementoAgregar, conEliAgre);
											  }
											else if(conModi == 2) 
											  {
											    continuar2 = false;
											  }
										    else 
										      {
											    System.out.println("Ejecute una opcion valida: ");
										      }
										}
									   		   
										restaurant.agregarProductoMenu(codigoProduct);	
										
									 }
							    
								 else if(codigoProduct >= 300 && codigoProduct <= 303)
						     	     {
									 restaurant.agregarProductoCombo(codigoProduct);
						     	     }			
								   
								  else 
								  {
									System.out.println("Digite alguno de los combos o elementos del menu con su codigo correcto");
								  }
									   
								   int conti = Integer.parseInt(input("\n¿Desea agregar otro producto a su lista(1. Si 2.No)? \n"));
									
								   if (conti == 2)
									   {  
									         System.out.println("*\n***********PRODUCTOS EN FACTURA*************\n");
									         restaurant.mostrarProductos();
									    	continuarmodi = false;
									   }
						
	                    } 	
					}
				
	                else if (opcion_seleccionada == 4)
	                {
	                	boolean esta =restaurant.buscarIgualPedido();
	                	if(esta) {
	                		System.out.println("\nEXISTE UN PEDIDO IGUAL AL ACTUAL. AUN ASI SE AGREGARA EL PEDIO...GRACIAS :)");
	                	}
	                	else
	                	{
	                		System.out.println("\nNO HA EXISTIDO NINGUN PEDIDO IGUAL AL ACTUAL");
	                	}
	                	restaurant.cerrarYGuardarPedido();
	                	System.out.println("\nPEDIDO AGREGADO CON EXITO!!!");
	                }
					else if (opcion_seleccionada==5)
					{
						restaurant.mostrarId();
						String codigoPedido = input("\nIngrese el ID del pedido a buscar \n");
                         
						restaurant.buscarPedidoID(codigoPedido);
						
					}
					else if (opcion_seleccionada==6)
					{
					
						System.out.println("Saliendo de la aplicación ...");
						continuar = false;
					}
					
					else
					{
						System.out.println("Por favor seleccione una opción válida.");
					}
				}
				catch (NumberFormatException e)
				{
					System.out.println("Debe seleccionar uno de los números de las opciones.");
				}
			}
		}

	 
	public void mostrarMenu()
     {
		String nombreMenu = "./data/menu.txt";
		String nombreCombo = "./data/combos.txt";
		String nombreIngredientes = "./data/ingredientes.txt";
		String nombreBebidas = "./data/bebidas.txt";
		
		
		restaurant.cargarInformacionRestaurante(nombreMenu, nombreCombo, nombreIngredientes,nombreBebidas);
		
     
     }

	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
	
	public void mostrarOpciones()
	{
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("1. Mostrar el menú");
		System.out.println("2. Iniciar un nuevo pedido");
		System.out.println("3. Agregar un elemento a un pedido");
		System.out.println("4. Cerrar un pedido y guardar factura - verifica antes si existe un pedido o no CON LOS MISMOS PRODUCTOS");
		System.out.println("5. Consultar la informacion de un pedido dado su id");
		System.out.println("6. Salir de la aplicacion");
	}
	
	public static void main(String[] args)
	{
		Aplicacion consola = new Aplicacion();
		consola.ejecutarOpcion();
		
	}
}
