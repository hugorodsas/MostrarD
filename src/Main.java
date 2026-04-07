import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url="jdbc:oracle:thin:@localhost:1521:xe";
        String user="RIBERA";
        String password="ribera";

        try (Connection conn = DriverManager.getConnection(url,user,password);
             Statement statement = conn.createStatement()){
            String nombreDepartamento = "Desarrollo";

            String sql = "SELECT e.id, e.nombre, e.salario, d.nombre AS departamento " +
                    "FROM EMPLEADO3 e " +
                    "JOIN DEPARTAMENTO3 d ON e.departamento_id = d.id " +
                    "WHERE d.nombre = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

                preparedStatement.setString(1, nombreDepartamento);
                try (ResultSet rs = preparedStatement.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String nombre = rs.getString("nombre");
                        double salario = rs.getDouble("salario");
                        String dept = rs.getString("departamento");

                        System.out.printf("ID: "+id+", Nombre: "+nombre+", Salario: "+salario+", Dep: "+dept);
                    }
                }
            }
        }catch (SQLException e){
            System.out.println("ERROR: "+e.getMessage());
        }
    }
}
