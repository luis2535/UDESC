#include <stdio.h>
#include <locale.h>
#include <stdlib.h>

char nome_familia[50][500];//vai receber os nomes dos familiares da mesma casa. 
int leitura_dados, atend_dia, a_d=0, data, i, a=0, nSintomas, sint, vetSin[10], n_familia; 

struct cadastro{//nome do médico e dados do paciente
	    char nome_medico[100];
		char nome[50];
		int idade;
		int cpf;
		int cep;		
	}cadastroCOVID;
	
struct data{//data do dia dos atendimentos 
	int dia;
	int mes;
	int ano;
    }dataATEND;	

void menu(){// o menu aparecerá para cada novo paciente no pronto socorro 
    struct cadastro cadastroCOVID;
	puts("\t-------------------------------------------------------------------------------");
	puts("\t|      BEM-VINDO AO CENTRO DE TRIAGEM COVID-19     |");
	puts("\t-------------------------------------------------------------------------------");
	puts("\tAqui receberemos suas informações e iremos direcionar você aos próximos passos!\n");
	puts("\tVAMOS INICIAR O SEU CADASTRO: *(passe álcool nas mãos antes de usar o computador)*\n");
    
    printf("\tNOME: ");
        gets(cadastroCOVID.nome);    
	printf("\tIDADE: ");
		scanf("%d",&cadastroCOVID.idade);
	printf("\tCPF (sem ponto/traço): ");
		scanf("%d", &cadastroCOVID.cpf);
	printf("\tCEP (sem ponto/traço): ");
		scanf("%d", &cadastroCOVID.cep);
    
		
}

void sintoma(){// cada atendimento receberá a função sintoma 
	printf("\n\tDigite a QUANTIDADE de sintomas que você está  sentido: ");
    	scanf("%d", &nSintomas);
    
 	printf("\n\tDos seus %d sintomas, digite seus respectivos números: ", nSintomas);
	    puts("\n\t\t(1) Tosse seca");
		puts("\n\t\t(2) Coriza");
		puts("\n\t\t(3) Dor de garganta ");
		puts("\n\t\t(4) Perda de paladar  ");
		puts("\n\t\t(5) Perda de olfato  ");
		puts("\n\t\t(6) Falta de apetite");
		puts("\n\t\t(7) Cansaço ");
		puts("\n\t\t(8) Febre ");
		puts("\n\t\t(9) Dor de cabeça");
		puts("\n\t\t(10) Falta de ar");
	
	
	for (i=0; i<nSintomas; i++){//construindo o vetor guarda os números que identificam cada sintoma
	printf("\n\tNº:");
	scanf("%d", &sint);
	vetSin[i]= sint;
    }
    
    
} 
	void familia(){ //recebe os dados que vão estar junto ao atestado de isolamento 
		puts("\n\tSerá necessário isolamento domiciliar até o resultado do teste.");
		puts("\n\tCaso seu teste resultar em positivo, o tempo de isolamento poderá aumentar.");
		printf("\n\tDigite o número de pessoas que moram com você:");
	scanf("%d", &n_familia);
	
	for (a=0;a<n_familia;a++)
        {
        	printf ("\tDigite o nome: \n");
        	fflush(stdin);//pq não estava pedindo para digitar o primeiro nome
        	
    }
}
	
	void isolamento(){//atestado com as informações do paciente e das pessoas que moram com ele
		puts("\n\t---------------------------------------------------------------------");
    	puts("\t|      ATESTATO MÉDICO E DECLARAÇÕES DO PRONTO SOCORRO    |");
    	puts("\t---------------------------------------------------------------------");
		printf("\tSr(a). %s,\n", cadastroCOVID.nome);	
		puts("\n\tSeguindo orientações do ministério da saúde, atesto que você e as ");
        puts("\tpessoas listadas abaixo devem permanecer em isolamento domiciliar por ");
        puts("\t14 dias ou até sair o resultado do teste (se não reagente).");
		printf("\tAfasando-se das atividades laborais ou escolares");
		printf("\n\ta partir de %d/%d/%d (hoje),", dataATEND.dia, dataATEND.mes, dataATEND.ano);
	    printf("\n\tpor residirem no mesmo domicílio que Sr(a) %s.\n" , cadastroCOVID.nome);
	    
	    
	    puts("\n\n\n\n\tPessoas que residem no mesmo domicílio que o paciente:");
	   for (a=0;a<n_familia;a++)//criando o vetor que guarda os nomes dos familiares
        { 
          printf("\n\t-%s",nome_familia[a]);
      }	
       puts("\n\n\n\n\n\n");
       printf("\n\t\t\t\tMédico responsável: %s\n\n\n", cadastroCOVID.nome_medico);
       puts("\n\t---------------------------------------------------------------------");
	}


	

int main(){
		int sintoma_8 = 0;
        int j=0;
		
	setlocale(LC_ALL,"");
	puts("***QUERIDO ATENDENTE, DIGITE AS INFORMAÇÕES DOS PACIENTES.***");
	puts("\n\tDIGITE A DATA DE HOJE");	
    	printf("\tDIA:");
		scanf("%d", &dataATEND.dia);
        printf("\tMÊS:");
		scanf("%d", &dataATEND.mes);
        printf("\tANO:");
		scanf("%d", &dataATEND.ano);
		
	puts("\n\tDigite a quantidade de atendimentos que poderão ser realizados hoje: ");
	scanf("%d", &atend_dia);
    menu();
}