import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Kayak {
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static ArrayList<Reserva> reservas = new ArrayList<>();
    private static Scanner scanner;
    private static Usuario account = null;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        try {
            usuarios = loadDataUsuario();
            reservas = loadDataReservas();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        boolean loginCicle = true;

        while (loginCicle) {
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");
            System.out.println("Ingrese la opción que desea: ");
            int opt = scanner.nextInt();

            switch (opt) {
                case 1:
                    try {
                        scanner.nextLine();
                        System.out.println("Ingrese su usuario");
                        String user = scanner.nextLine();
                        System.out.println("Ingrese su contraseña");
                        String password = scanner.nextLine();
                        account = loginUser(user, password);
                        System.out.println("Usurio ingresado con exito");
                        loginCicle = false;
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                System.out.println("==================== CREACIÓN DE USUARIO =====================");
                try {
                    scanner.nextLine();
                    System.out.print("Usuario: ");
                    String usuario = scanner.nextLine();
                    System.out.print("Contraseña: ");
                    String password = scanner.nextLine();
                    System.out.println("1.Base ");
                    System.out.println("2.Vip ");
                    System.out.print("Tipo: ");
                    int sel1 = scanner.nextInt();
                    String tipodecliente = (sel1 == 1) ? "BASE" : "VIP";
                    if(tipodecliente.equals("VIP")){
                        System.out.println("Este tipo de usuario aun no esta disponible, por lo que se le asignara como base!");
                        tipodecliente = "BASE";
                    }
                    usuarios.add(new Usuario(usuarios.size()+1, usuario, password, tipodecliente));
                    updateDataUsuarios(usuarios);
                    System.out.println("Usuario creado con éxito!");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
                case 3:
                    System.out.println("Saliendo del programa");
                    loginCicle = false;
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
        if(account!= null){
            boolean menuCicle = true;
            while(menuCicle){
                System.out.println("1. Cambiar tipo cliente ");
                System.out.println("2. Aplicar descuento");
                System.out.println("3. Cambiar contraseña");
                System.out.println("4. Hacer reserva");
                System.out.println("5. Imprimir itinerario");
                System.out.println("6. Salir");
                System.out.println("Ingrese la opción que desea: ");
                int opt = scanner.nextInt();
                switch (opt) {
                    case 1:
                        System.out.println("Este opcion aun no esta disponible, disculpe la molestia!");
                        break;
                    case 2:
                        account.aplicarDescuentoCupon();
                        break;
                    case 3:
                        try {
                            scanner.nextLine();
                            System.out.println("Ingrese su contraseña");
                            String password1 = scanner.nextLine();
                            System.out.println("Repita la contraseña correctamente");
                            String password2 = scanner.nextLine();
                            if(password1.equals(password2)){
                                account.cambiarContraseña(password1);
                                System.out.println("Se ha actualizado la contraseña con exito!");
                            }else{
                                throw new Exception("Las contraseñas ingresadas con coinciden.");
                            }                    
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 4:
                        try {
                        System.out.println("Realizar reserva y confirmacion");
                        scanner.nextLine();
                        System.out.println("Ingrese la fecha del viaje (en formato AAAA-MM-DD):");
                        String fechaViajeStr = scanner.nextLine();
                        LocalDate fechaViaje = LocalDate.parse(fechaViajeStr);

                        System.out.println("Ingrese el tipo de vuelo (Coach o Primera Clase):");
                        System.out.println("1. Primera clase");
                        System.out.println("2. Coach");
                        String selt = scanner.next();
                        String tipoVuelo = null;
                        switch (selt) {
                            case "1":
                                tipoVuelo = "Primera Clase";
                            case "2":
                                tipoVuelo = "Coach";
                            default:
                                System.out.println("Tipo de vuelo no válido. Por favor, sera coach por default.");
                                tipoVuelo = "Coach";
                        }
                        scanner.nextLine();
                        System.out.println("Ingrese la cantidad de boletos:");
                        int cantidadBoletos = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Ingrese la aerolínea:");
                        String aerolinea = scanner.nextLine();
                  
                        account.hacerReserva(fechaViaje, tipoVuelo, cantidadBoletos, aerolinea);
                        System.out.println("Reserva realizada con exito"); 
                        System.out.println(account.obtenerInformacionReserva());   

                        System.out.println("Ingrese el número de tarjeta:");
                        String numeroTarjeta = scanner.nextLine();

                        System.out.println("Ingrese la cantidad de cuotas:");
                        int cantidadCuotas = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Ingrese la clase de vuelo:");
                        String claseVuelo = scanner.nextLine();

                        account.hacerConfirmacion(numeroTarjeta, cantidadCuotas, claseVuelo); 
                        System.out.println(account.obtenerInformacionConfirmacion());
                        if(account.getReserva().getConfirmado() ==  true){
                            reservas.add(account.getReserva());
                            System.out.println("Confirmacion realizada con exito y agregada a la base de datos"); 
                        }else{
                            throw new Exception("La reserva no esta confirmada, no se guardara en la base de datos");
                        }
                        
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 5:
                        try {
                        System.out.println("Selecciona la reserva a la que quieres imprimir su itinerario:");
                        int index= 0;
                        for(Reserva reserva: reservas){
                            if(reserva.getIdUsuario() == account.getUsuarioId()){
                                System.out.println((index +1) + ". Fecha de vuelo:" + reserva.getFechaViaje());
                            }
                            index++;
                        }
                        int select = scanner.nextInt();
                        account.setReserva(reservas.get(select));
                        account.imprimirItinerario();
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 6:
                        System.out.println("Saliendo del programa");
                        menuCicle = false;
                        break;
                    default:
                        System.out.println("Opción no válida");
                        break;
                }
            }

        }
        try {
            updateDataReservas(reservas);
            updateDataUsuarios(usuarios);
        } catch (Exception e) {
            System.out.println("Error actualizando los datos");
        }
        
    }


    public static ArrayList<Usuario> loadDataUsuario() throws Exception{
        try{
            ArrayList<Usuario> temp = new ArrayList<Usuario>();  
            Scanner scanner = new Scanner(new File("usuarios.csv"));
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] valores = linea.split("\\,");
                temp.add(new Usuario(Integer.parseInt(valores[0]),valores[1],valores[2],valores[3])); 
            }
            return temp;
        }catch(Exception e){
            throw new Exception(e);
        }
    }

    public static ArrayList<Reserva> loadDataReservas() throws Exception{
        try{
            ArrayList<Reserva> temp = new ArrayList<Reserva>();  
            Scanner scanner = new Scanner(new File("reservas.csv"));
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] valores = linea.split("\\,");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fecha = LocalDate.parse(valores[1], formatter);
                Reserva res = new Reserva(Integer.parseInt(valores[0]),fecha,valores[2],Integer.parseInt(valores[3]),valores[4]);
                res.setConfirmado(Boolean.parseBoolean(valores[5]));
                res.setNumeroTarjeta(valores[6]);
                res.setCuotas(Integer.parseInt(valores[7]));
                 res.setClaseVuelo(valores[8]);
                temp.add(res); 
            }
            return temp;
        }catch(Exception e){
            throw new Exception(e);
        }
    }
    /**
     * Actualiza los datos en el archivo csv.
     *
     * @throws Exception Si ocurre un error al subir los datos.
     */

    public static void updateDataUsuarios(ArrayList<Usuario> usuarios)throws Exception{
        try(FileWriter escritor = new FileWriter("usuarios.csv")){
            for (Usuario usuario : usuarios) {
                    escritor.append(String.valueOf(usuario.getUsuarioId())+","+usuario.getNombre()+","+usuario.getContraseña()+","+usuario.getTipo());
                    escritor.append("\n");
                    
                }
            }
        catch (IOException e){
            throw new Exception(e);
        }        
    }

    public static void updateDataReservas(ArrayList<Reserva> reservas)throws Exception{
        try(FileWriter escritor = new FileWriter("reservas.csv")){
            for (Reserva reserva : reservas) {
                    escritor.append(String.valueOf(reserva.getIdUsuario())+","+String.valueOf(reserva.getFechaViaje())+","+reserva.getTipoVuelo()+","+String.valueOf(reserva.getCantidadBoletos())+","+reserva.getAerolinea()+","+String.valueOf(reserva.getConfirmado())+","+reserva.getNumeroTarjeta()+","+String.valueOf(reserva.getCantidadCuotas())+","+reserva.getClaseVuelo());
                    escritor.append("\n");
                    
                }
            }
        catch (IOException e){
            throw new Exception(e);
        }        
    }

    public static Usuario loginUser(String user, String password) throws Exception {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(user) && usuario.getContraseña().equals(password)) {
                return usuario;
            }
        }
        throw new Exception("Usuario con estas credenciales no encontrado");
    }
}


