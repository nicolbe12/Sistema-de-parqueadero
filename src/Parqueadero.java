import javax.swing.JOptionPane;

public class Parqueadero {
    public static void main(String[] args) {
        
        int opcion = 0;
        int puestos = 10;
        int[][] parqueadero = new int[puestos][3];

            for(int i = 0; i < puestos; i++){
                parqueadero[i][0] = i;
                parqueadero[i][1] = 0;
            }

            do {
                opcion = Integer.parseInt(JOptionPane.showInputDialog("=== PARQUEADERO ===\n" + "1. Registrar entrada\n"
                + "2. Registrar salida\n" + "3. Ver puestos\n" + "4. Salir"));

                switch(opcion) {
                    case 1:
                        int horaEntrada;
                        boolean disponible = false;

                        try {
                            horaEntrada = Integer.parseInt(
                                JOptionPane.showInputDialog("Ingrese la hora de entrada (0-23)"));
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null,"Debe ingresar solo numeros");

                            break;
                        }
                        if (horaEntrada < 0 || horaEntrada > 23) {
                            JOptionPane.showMessageDialog(null, "Hora invalida");
                            break;
                        }
                        for (int i = 0; i < puestos; i++) {
                            if (parqueadero[i][1] == 0) {
                                parqueadero[i][1] = 1;
                                parqueadero[i][2] = horaEntrada;

                                disponible = true;

                                JOptionPane.showMessageDialog(null, "Vehiculo registrado en el puesto " + i);

                                break;
                            }
                        }
                        if (!disponible) {
                            JOptionPane.showMessageDialog(null, "No hay puestos disponibles");
                        }
                    break;

                    case 2:
                        int puesto;
                        int horaSalida;
                        int tiempo;
                        int total;

                        try {
                            puesto = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el puesto del vehiculo"));
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null,"Debe ingresar un numero");
                            break;
                        }
                        if (puesto < 0 || puesto >= puestos) {
                            JOptionPane.showMessageDialog(null,"Puesto invalido");
                            break;
                        }
                        if (parqueadero[puesto][1] == 0) {
                            JOptionPane.showMessageDialog(null,"El puesto esta libre");
                            break;
                        }
                        try {
                            horaSalida = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la hora de salida"));
                        } catch (Exception e) {JOptionPane.showMessageDialog(null,"Debe ingresar solo numeros");
                            break;
                        }
                        if (horaSalida < 0 || horaSalida > 23) {
                            JOptionPane.showMessageDialog(null,"Hora invalida");
                            break;
                        }
                        //Solo funciona para registros del mismo dia, porque la hora de salida no puede ser menor a la de entrada
                        tiempo = horaSalida - parqueadero[puesto][2];
                        if (tiempo <= 0) {
                            JOptionPane.showMessageDialog(null, "La hora de salida no es valida");
                            break;
                        }
                        total = tiempo * 2000; //Por ahora

                        JOptionPane.showMessageDialog(null,"Tiempo: " + tiempo + " horas\n" 
                        + "Total a pagar: $" + total);

                        parqueadero[puesto][1] = 0; //libera el puesto
                    break;

                    case 3:
                        String estado = "";
                        for (int i = 0; i < puestos; i++) {
                            if (parqueadero[i][1] == 0) {
                                estado += "Puesto " + i + " - Libre\n";
                            } else {
                                estado += "Puesto " + i + " - Ocupado\n";
                            }
                    }    
                        JOptionPane.showMessageDialog(null, estado);
                    break;

                    case 4:
                        //salir del menú
                    break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opción inválida");
                }
            } while (opcion != 4);

        JOptionPane.showMessageDialog(null, "ya funciona?");
    }

}