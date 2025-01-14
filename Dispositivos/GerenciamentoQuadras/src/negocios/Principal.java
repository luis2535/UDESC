package negocios;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;

import dados.*;
import negocios.Sistema;
import excecoes.*;
public class Principal {
//	public static void main(String[] args) {
//        try {
//            Sistema sistema = Sistema.getInstance();
//
//            // Exemplo de uso das funções do sistema
//            Usuario usuario = new Usuario("71992758111", "Fulano", "Silva", "fulano@example.com", "senha123", "ATIVO");
//            sistema.insereUsuario(usuario);
//
//            Usuario usuarioBuscado = sistema.buscaUsuario("71992758972");
//            System.out.println("Usuário encontrado: " + usuarioBuscado);
//
//            usuarioBuscado.setUnome("Santos");
//            sistema.atualizaUsuario(usuarioBuscado);
//
//            List<Usuario> usuarios = sistema.buscaUsuarios();
//            System.out.println("Usuários encontrados:");
//            for (Usuario u : usuarios) {
//                System.out.println(u);
//            }
//
//            sistema.deletaUsuario(usuarioBuscado);
//
//            usuarios = sistema.buscaUsuarios();
//            System.out.println("Usuários encontrados após exclusão:");
//            for (Usuario u : usuarios) {
//                System.out.println(u);
//            }
            //Bolsista bolsista = new Bolsista("71992758972", "Fulano", "Silva", "fulano@example.com", "senha123", "ATIVO", 1, "Civil");
            //sistema.insereBolsista(bolsista);
            
//            Bolsista bolsistaBuscado = sistema.buscaBolsista("71992758972");
//            System.out.println("Usuário encontrado: " + bolsistaBuscado);
//
//            bolsistaBuscado.setUnome("Ramalho");
//            sistema.atualizaBolsista(bolsistaBuscado);
//
//            List<Bolsista> bolsistas = sistema.buscaBolsistas();
//            System.out.println("Usuários encontrados:");
//            for (Bolsista b : bolsistas) {
//                System.out.println(b);
//            }
//            
//            sistema.deletaBolsista(bolsistaBuscado);
//            
//            bolsistas = sistema.buscaBolsistas();
//            System.out.println("Após exclusao: ");
//            for(Bolsista b : bolsistas) {
//            	System.out.println(b);
//            }
            
//            Servidor servidor = new Servidor("71992758972", "Fulano", "Silva", "fulano@example.com", "senha123", "ADMIN",1, "Civil");
//            sistema.insereServidor(servidor);
//            
//            Servidor servidorBuscado = sistema.buscaServidor("71992758972");
//            System.out.println("Usuário encontrado: " + servidorBuscado);
//
//            servidorBuscado.setUnome("Alves");
//            sistema.atualizaServidor(servidorBuscado);
//
//            List<Servidor> servidores = sistema.buscaServidores();
//            System.out.println("Usuários encontrados:");
//            for (Servidor s : servidores) {
//                System.out.println(s);
//            }
//            
//            sistema.deletaServidor(servidorBuscado);
//            
//            servidores = sistema.buscaServidores();
//            System.out.println("Após exclusao: ");
//            for(Servidor s : servidores) {
//            	System.out.println(s);
//            }

//            Bloco bloco = new Bloco(3, "asd", "asd");
//            sistema.insereBloco(bloco);
//           Bloco blocoBuscado = sistema.buscaBloco(3);
//           System.out.println("Bloco Encontrado: "+blocoBuscado);
//           
//           blocoBuscado.setNome("A");
//           List<Bloco> blocos = sistema.buscaBlocos();
//           for(Bloco b : blocos) {
//        	   System.out.println(b);
//           }
//           
//           sistema.deletaBloco(blocoBuscado);
//           blocos = sistema.buscaBlocos();
//           for(Bloco b : blocos) {
//        	   System.out.println(b);
//           }
//           
           
//           Quadra quadra = new Quadra(1, "Futsal", "Quadra de futsal", sistema.buscaBloco(1));
//           sistema.insereQuadra(quadra);
//           
//           Quadra quadraBuscada = sistema.buscaQuadra(3);
//           System.out.println("Quadra encontrada: "+quadraBuscada);
//           
//           quadraBuscada.setModalidade("Futsal e volei");
//           sistema.atualizaQuadra(quadraBuscada);
//           List<Quadra> quadras = sistema.buscaQuadras();
//           for(Quadra q : quadras) {
//        	   System.out.println(q);
//           }
//           sistema.deletaQuadra(quadraBuscada);
//           System.out.println("Após delete");
//           quadras = sistema.buscaQuadras();
//           for(Quadra q : quadras) {
//        	   System.out.println(q);
//           }
           
//           Quadra quadra = sistema.buscaQuadra(4);
//           Usuario usuario = sistema.buscaUsuario("71992758972");
//
//           Agendamento agendamento = new Agendamento(1, "10:00:00", "12:00:00", "2024-05-30", "Ativo", sistema.buscaUsuario("123456789"), sistema.buscaQuadra(2));
//           Reserva reserva = new Reserva(1, agendamento, sistema.buscaEquipamento(2),2);
//           sistema.insereAgendamento(reserva);
//           
//           Agendamento agendamentobuscado = sistema.buscaAgendamento(66);
//           System.out.println(agendamentobuscado);
//           
//           agendamentobuscado.setStatus("CANCELADO");
//           sistema.atualizaAgendamento(agendamentobuscado);
//           
//           List<Agendamento> agendamentos = sistema.buscaAgendamentos();
//           for(Agendamento a : agendamentos) {
//        	   System.out.println(a);
//           }
           
//           sistema.deletaAgendamento(reserva);
//           agendamentos = sistema.buscaAgendamentos();
//           for(Agendamento a : agendamentos) {
//        	   System.out.println(a);
//           }
//           Equipamento equipamento = new Equipamento(1, "Bola de Futsal", "Bola da Nike");
//           sistema.insereEquipamento(equipamento);
//           Equipamento equipamentoBuscado = sistema.buscaEquipamento(1);
//           System.out.println(equipamentoBuscado);
//           equipamentoBuscado.setDescricao("Bola da ADIDAS");
//           sistema.atualizaEquipamento(equipamentoBuscado);
//           List<Equipamento> equipamentos = sistema.buscaEquipamentos();
//           for(Equipamento e : equipamentos) {
//        	   System.out.println(e);
//           }
//           sistema.deletaEquipamento(equipamentoBuscado);
//           equipamentos = sistema.buscaEquipamentos();
//           for(Equipamento e : equipamentos) {
//        	   System.out.println(e);
//           }
//        Evento evento = new Evento(2, "2023-05-30", "12:00", "14:00", "Ativo", "Evento na quadra", sistema.buscaServidor("123456789"), sistema.buscaQuadras());
//        sistema.insereEvento(evento);
//         Evento eventos = sistema.buscaEvento(44);
//         System.out.println(eventos);
//         sistema.deleteEvento(eventos);
//         List<Evento> Eventos = sistema.buscaEventos();
//         for(Evento e : Eventos) {
//        	 System.out.println(e);
//         }
//       
//         Usuario user = sistema.buscaUsuario("71992758974");
//         Quadra quadra = sistema.buscaQuadra(4);
//         Equipamento equip = new Equipamento(1, "Bola de Futsal", "Bola 8");
//         sistema.insereEquipamento(equip);
//         System.out.println("Equipamento adicionado");
//         Agendamento agendamento = new Agendamento(1, "6:00", "12:00", "2023-06-14", "Ativo", user, quadra );
//         Reserva reserva = new Reserva(1, agendamento, equip, 1);
//         sistema.insereAgendamento(reserva);
//         System.out.println("Feito");
//         
//         
//            Agendamento agendamento = sistema.buscaAgendamento(2);
//            Equipamento equip = sistema.buscaEquipamento(1);
//            
//            Reserva reserva = new Reserva(1, agendamento, equip, 1);
//            
//            sistema.deletaAgendamento(reserva);
//            
//            System.out.println("Feito");
            
            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//       
//    }

}
