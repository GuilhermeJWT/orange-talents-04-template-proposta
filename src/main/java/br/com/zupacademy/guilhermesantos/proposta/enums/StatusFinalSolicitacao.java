package br.com.zupacademy.guilhermesantos.proposta.enums;

public enum StatusFinalSolicitacao {
	
	COM_RESTRICAO(StatusProposta.NAO_ELEGIVEL),
	SEM_RESTRICAO(StatusProposta.ELEGIVEL);

	private StatusProposta status;

	StatusFinalSolicitacao(StatusProposta status){
		this.status = status;
	}

	public StatusProposta getStatus() {
		return status;
	}

	public void setStatus(StatusProposta status) {
		this.status = status;
	}
}
