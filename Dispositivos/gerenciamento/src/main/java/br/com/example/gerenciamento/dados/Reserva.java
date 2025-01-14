package br.com.example.gerenciamento.dados;

public class Reserva {

	private int id_reserva;
	private Agendamento agendamento;
	private Equipamento equipamento;
	private int quantidade_equip;
	
	public Reserva() {}
	
	public Reserva(int id_reserva, Agendamento agendamento, Equipamento equipamento, int quantidade_equip) {
		this.id_reserva = id_reserva;
		this.agendamento = agendamento;
		this.equipamento = equipamento;
		this.quantidade_equip = quantidade_equip;
	}

	public int getId_reserva() {
		return id_reserva;
	}

	public void setId_reserva(int id_reserva) {
		this.id_reserva = id_reserva;
	}

	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public int getQuantidade_equip() {
		return quantidade_equip;
	}

	public void setQuantidade_equip(int quantidade_equip) {
		this.quantidade_equip = quantidade_equip;
	}

	@Override
	public String toString() {
		return "Reserva [id_reserva=" + id_reserva + ", agendamento=" + agendamento + ", equipamento=" + equipamento
				+ ", quantidade_equip=" + quantidade_equip + "]";
	}
	
}
