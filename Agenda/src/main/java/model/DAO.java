package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * The Class DAO.
 */
public class DAO {
	
	/** The driver. */
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private String url = "jdbc:mysql://127.0.0.1:3306/agenda?useTimezone=true&serverTimezone=UTC";
	
	/** The user. */
	private String user = "root";
	
	/** The pass. */
	private String pass = "";
	
	/**
	 * Connect.
	 *
	 * @return the connection
	 */
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pass);
			return con;
		} catch (Exception e) {
			System.out.print("Erro ao connectar a Base de dados" + e.getMessage());
			return null;
		}
	}
	
	/**
	 * Insert contact.
	 *
	 * @param bean the bean
	 */
	public void insertContact(JavaBeans bean) {
		String insert = "INSERT INTO `contact`(`name`, `phone`, `email`) VALUES (?,?,?)";
		try {
			Connection conn = connect();
			PreparedStatement preparedInsert = conn.prepareStatement(insert);
			preparedInsert.setString(1, bean.getNome());
			preparedInsert.setString(2, bean.getPhone());
			preparedInsert.setString(3, bean.getEmail());
			
			preparedInsert.executeUpdate();
			
			conn.close();
		} catch (Exception e) {
			System.out.println("Erro ao Inserir dados " + e.getMessage());
		}
	}
	
	/**
	 * Gets the all contacts.
	 *
	 * @return the all contacts
	 */
	public ArrayList<JavaBeans> getAllContacts(){
		ArrayList<JavaBeans> allContacts = new ArrayList<>();
		
		String select = "SELECT * FROM `contact`";
		try {
			Connection conn = connect();
			PreparedStatement preparedSelect = conn.prepareStatement(select);
			
			ResultSet rs = preparedSelect.executeQuery();
			
			while(rs.next()) {
				int id = Integer.parseInt(rs.getString(1));
				String name = rs.getString(2);
				String phone = rs.getString(3);
				String email = rs.getString(4);
				
				allContacts.add(new JavaBeans(id, name, phone, email));
			}
			conn.close();
			return allContacts;
			
		} catch (Exception e) {
			System.out.println("Erro ao Listar " + e.getMessage());
			return null;
		}
		
	}
	
	/**
	 * Select one contact.
	 *
	 * @param bean the bean
	 */
	public void selectOneContact(JavaBeans bean) {
		String selectContact = "SELECT * FROM `contact` WHERE id = ?";
		try {
			Connection conn = connect();
			PreparedStatement preparedSelectOne = conn.prepareStatement(selectContact);
			preparedSelectOne.setInt(1, bean.getId());
			ResultSet rs = preparedSelectOne.executeQuery();
			while(rs.next()) {
				bean.setId(rs.getInt(1));
				bean.setNome(rs.getString(2));
				bean.setPhone(rs.getString(3));
				bean.setEmail(rs.getString(4));
			}
			conn.close();
		} catch (Exception e) {
			System.out.println("Erro ao seleccionar " + e.getMessage());
		}
	}
	
	/**
	 * Update contact.
	 *
	 * @param bean the bean
	 */
	public void updateContact(JavaBeans bean) {
		String update = "UPDATE `contact` SET `name`=?,`phone`=?,`email`=? WHERE id = ?";
		try {
			Connection conn = connect();
			PreparedStatement preparedUpdate = conn.prepareStatement(update);
			preparedUpdate.setString(1, bean.getNome());
			preparedUpdate.setString(2, bean.getPhone());
			preparedUpdate.setString(3, bean.getEmail());
			preparedUpdate.setInt(4, bean.getId());
			
			preparedUpdate.executeUpdate();
			
			conn.close();
		} catch (Exception e) {
			System.out.println("Erro ao efectuar o update - " + e.getMessage());
		}
	}
	
	/**
	 * Delete contact.
	 *
	 * @param bean the bean
	 */
	public void deleteContact(JavaBeans bean) {
		String delete = "DELETE FROM `contact` WHERE id = ?";
		try {
			Connection conn = connect();
			PreparedStatement preparedDelete = conn.prepareStatement(delete);
			preparedDelete.setInt(1, bean.getId());
			
			preparedDelete.executeUpdate();
			
			conn.close();
		} catch (Exception e) {
			System.out.println("Erro ao apagar dados" + e.getMessage());
		}
	}

}
