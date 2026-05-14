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
                        //registrar la entrada
                    break;

                    case 2:
                        //Registrar la salida

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
                        //salir del parqueadero
                    break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opción inválida");
                }
            } while (opcion != 4);

        JOptionPane.showMessageDialog(null, "ya funciona?");
    }

}