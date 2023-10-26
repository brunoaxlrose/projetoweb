package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Cliente;
import util.ConectaDB;

public class ClienteDAO {
    //Métodos

    public boolean cadastrarCliente(Cliente cliente) {
        Connection conexao = null;
        try {
            conexao = ConectaDB.conectar();
            Statement stmt = conexao.createStatement();
            String sql = "INSERT into cliente(nome, rg, cpf, email, telefone, celular, cep, rua, numero, complemento, bairro, cidade, uf) "
                    + "values('" + cliente.getNome() + "','" + cliente.getRg() + "','" + cliente.getCpf()
                    + "', '" + cliente.getEmail() + "','" + cliente.getTelefone() + "','" + cliente.getCelular()
                    + "', '" + cliente.getEnderecoCep() + "','" + cliente.getEnderecoRua() + "','" + cliente.getEnderecoNumero()
                    + "', '" + cliente.getEnderecoComplemento() + "','" + cliente.getEnderecoBairro() + "','" + cliente.getEnderecoCidade()
                    + "','" + cliente.getEnderecoUf() + "')";

            stmt.executeUpdate(sql); // Insert, Delete ou Update            
            System.out.println("Registro Inserido!");
            conexao.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Exception: " + ex.toString());
            return false;
        }
    }

    public Cliente consultarCpf(Cliente cliente) {
        Connection conexao = null;
        try {
            conexao = ConectaDB.conectar();
            Statement stmt = conexao.createStatement();
            String sql = "SELECT * from cliente WHERE cpf = '" + cliente.getCpf() + "'";
            ResultSet rs = stmt.executeQuery(sql);

            int n_reg = 0;
            while (rs.next()) {
                cliente.setNome(rs.getString("nome"));
                cliente.setRg(rs.getString("rg"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setEnderecoCep(rs.getString("cep"));
                cliente.setEnderecoRua(rs.getString("rua"));
                cliente.setEnderecoNumero(rs.getString("numero"));
                cliente.setEnderecoComplemento(rs.getString("complemento"));
                cliente.setEnderecoBairro(rs.getString("bairro"));
                cliente.setEnderecoCidade(rs.getString("cidade"));
                cliente.setEnderecoUf(rs.getString("uf"));
                n_reg++;
            }
            conexao.close();

            if (n_reg == 0) {
                return null;
            } else {
                return cliente;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Exception: " + ex.toString());
            return null;
        }
    }

    // Método para listar todos os clientes
    public List<Cliente> listarClientes() {
        Connection conexao = null;
        try {
            // Criar a lista dos clientes
            List<Cliente> lista = new ArrayList<>();

            // Comando SQL SELECT
            String sql = "SELECT * FROM tb_clientes";
            try (PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Cliente cliente = new Cliente();

                    cliente.setId_Nome(rs.getInt("id"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setRg(rs.getString("rg"));
                    cliente.setCpf(rs.getString("cpf"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setTelefone(rs.getString("telefone"));
                    cliente.setCelular(rs.getString("celular"));
                    cliente.setEnderecoCep(rs.getString("cep"));
                    cliente.setEnderecoRua(rs.getString("rua"));
                    cliente.setEnderecoNumero(rs.getString("numero"));
                    cliente.setEnderecoComplemento(rs.getString("complemento"));
                    cliente.setEnderecoBairro(rs.getString("bairro"));
                    cliente.setEnderecoCidade(rs.getString("cidade"));
                    cliente.setEnderecoUf(rs.getString("estado"));

                    // Adicionar cliente à lista
                    lista.add(cliente);
                }
            }
            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }
    }

    // Método para excluir cliente
    public void excluirCliente(Cliente cliente) {
        Connection conexao = null;
        try {
            // Comando para deletar na tabela cliente pelo CPF.
            String sql = "DELETE FROM cliente WHERE cpf = ?";

            // Conectar ao banco de dados e organizar o comando SQL.
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, cliente.getCpf());

                // Executar comando SQL
                stmt.executeUpdate();
            }

            System.out.println("Dados do cliente excluídos com sucesso");

        } catch (SQLException erroSql) {
            System.out.println("Erro: " + erroSql.toString());
        }
    }

    public boolean alterarCliente(Cliente cliente) {
        Connection conexao = null;

        try {
            conexao = ConectaDB.conectar();
            Statement stmt = conexao.createStatement();

            String sql = "UPDATE cliente SET "
                    + "nome='" + cliente.getNome() + "', "
                    + "rg='" + cliente.getRg() + "', "
                    + "telefone='" + cliente.getTelefone() + "', "
                    + "celular='" + cliente.getCelular() + "', "
                    + "cep='" + cliente.getEnderecoCep() + "', "
                    + "rua='" + cliente.getEnderecoRua() + "', "
                    + "numero='" + cliente.getEnderecoNumero() + "', "
                    + "complemento='" + cliente.getEnderecoComplemento() + "', "
                    + "bairro='" + cliente.getEnderecoBairro() + "', "
                    + "cidade='" + cliente.getEnderecoCidade() + "', "
                    + "uf='" + cliente.getEnderecoUf() + "' "
                    + "WHERE cpf = '" + cliente.getCpf() + "'";

            stmt.executeUpdate(sql); // Insert, Delete ou Update
            System.out.println("Registro Alterado com sucesso!");
            conexao.close();
            return true;

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro: " + ex.toString());
            return false;
        }
    }

    public boolean excluir(Cliente cliente) {
        Connection conexao = null;
        try {
            conexao = ConectaDB.conectar();
            Statement stmt = conexao.createStatement();

            String sql = "DELETE FROM cliente WHERE cpf = '" + cliente.getCpf() + "'";

            stmt.executeUpdate(sql); // Insert, Delete ou Update            
            System.out.println(" Registro Excluído! ");
            conexao.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(" Erro: " + ex.toString());
            return false;
        }
    }
}
