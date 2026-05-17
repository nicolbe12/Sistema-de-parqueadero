import javax.swing.JOptionPane;

public class Parqueadero {
    public static void main(String[] args) {
        
        int opcion = 0;
        int puestos = 10;
        int ganancias = 0;
        int[][] parqueadero = new int[puestos][4];
        String placas[] = new String[puestos];
        String propietarios[] = new String[puestos];

            for(int i = 0; i < puestos; i++){
                parqueadero[i][0] = i;
                parqueadero[i][1] = 0;
            }

            do {
                opcion = Integer.parseInt(JOptionPane.showInputDialog("=== PARQUEADERO ===\n" 
                + "1. Registrar entrada\n" + "2. Registrar salida\n" + "3. Ver puestos\n" + "4. Mostrar ganancias\n"
                + "5. Salir"));

                switch(opcion) {
                    case 1:
                        registrarEntrada(parqueadero, puestos, placas,propietarios);
                    break;

                    case 2:
                        ganancias = registrarSalida(parqueadero, puestos, placas, propietarios, ganancias);
                    break;

                    case 3:
                        //llama a la funcion
                        mostrarPuestos(parqueadero, puestos);
                    break;

                    case 4:
                        JOptionPane.showMessageDialog(null,"Ganancias del dia: $" 
                        + ganancias);
                    break;

                    case 5:
                        JOptionPane.showMessageDialog(null,"Saliendo de sistema.");
                    break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opción inválida");
                }
            } while (opcion != 5);

        JOptionPane.showMessageDialog(null, "bye");
    }

    public static void mostrarPuestos(int[][] parqueadero, int puestos) { // case 3
        String estado = "";
            for (int i = 0; i < puestos; i++) { 
                if (parqueadero[i][1] == 0) {
                    estado += "Puesto " + i + " - Libre\n";
                } else { estado += "Puesto " + i + " - Ocupado\n";}
            }  
        JOptionPane.showMessageDialog(null, estado);
    }

    public static void registrarEntrada(int[][] parqueadero,int puestos,String placas[],String propietarios[]) {
        int horaEntrada;
        boolean disponible = false;
        int tipoVehiculo;
        String placa;
        String propietario;
            try {
                horaEntrada = Integer.parseInt(
                JOptionPane.showInputDialog("Ingrese la hora de entrada (0-23)"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Debe ingresar solo numeros");
                return; 
            }
            if (horaEntrada < 0 || horaEntrada > 23) {
                JOptionPane.showMessageDialog(null, "Hora invalida");
                return;
            }
            
            try {
                tipoVehiculo = Integer.parseInt(JOptionPane.showInputDialog(
                "Tipo de vehiculo\n1. Carro\n2. Moto"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Debe ingresar solo numeros");
                return;
            }
            if (tipoVehiculo < 1 || tipoVehiculo > 2) {
                JOptionPane.showMessageDialog(null, "Tipo de vehiculo invalido");
                return;
            }

            placa = JOptionPane.showInputDialog("Ingrese la placa del vehiculo");
            if (placa.length() != 6) {JOptionPane.showMessageDialog(null,
                "La placa debe tener 6 caracteres");
                return;
            }
            propietario = JOptionPane.showInputDialog("Ingrese el nombre del propietario");

            for (int i = 0; i < puestos; i++) {
                if (parqueadero[i][1] == 0) {
                    parqueadero[i][1] = 1;
                    parqueadero[i][2] = horaEntrada;
                    parqueadero[i][3] = tipoVehiculo;
                    placas[i] = placa;
                    propietarios[i] = propietario;

                    disponible = true;
                    JOptionPane.showMessageDialog(null, "Vehiculo registrado en el puesto " + i);
                    break; //break solo funciona dentro de ciclos
                }
            }
            if (!disponible) {
                JOptionPane.showMessageDialog(null, "No hay puestos disponibles");
            }
    }

    public static int registrarSalida(int[][] parqueadero, int puestos,String placas[],String propietarios[], int ganancias) {
        int puesto;
        int horaSalida;
        int tiempo;
        int total;

            try {
                puesto = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el puesto del vehiculo"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Debe ingresar un numero");
                return ganancias;
            }
            if (puesto < 0 || puesto >= puestos) {
                JOptionPane.showMessageDialog(null,"Puesto invalido");
                return ganancias;
            }
            if (parqueadero[puesto][1] == 0) {
                JOptionPane.showMessageDialog(null,"El puesto esta libre");
                return ganancias;
            }
            try {
                horaSalida = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la hora de salida"));
            } catch (Exception e) {JOptionPane.showMessageDialog(null,"Debe ingresar solo numeros");
                    return ganancias;
                }
            if (horaSalida < 0 || horaSalida > 23) {
                JOptionPane.showMessageDialog(null,"Hora invalida");
                return ganancias;
            }
            //Solo funciona para registros del mismo dia, porque la hora de salida no puede ser menor a la de entrada
            tiempo = horaSalida - parqueadero[puesto][2];
            if (tiempo <= 0) {
                JOptionPane.showMessageDialog(null, "La hora de salida no es valida");
                return ganancias;
            }
            
            if (parqueadero[puesto][3] == 1) {
                total = tiempo * 3000;
            } else {
                total = tiempo * 2000;
            }

            String tipo;
            if (parqueadero[puesto][3] == 1) {
                tipo = "Carro";
            } else { tipo = "Moto";}

            JOptionPane.showMessageDialog(null, "Puesto: " + puesto + "\n" 
            + "Placa: " + placas[puesto] + "\n" + "Propietario: " + propietarios[puesto] + "\n"
            + "Tipo de vehiculo: " + tipo + "\n" + "Tiempo: " + tiempo + " horas\n" 
            + "Total a pagar: $" + total);
            parqueadero[puesto][1] = 0; //libera el puesto
            ganancias += total;
            return ganancias;
    }
}