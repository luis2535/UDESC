#include <stdio.h>
#include<locale.h>
#include<stdlib.h>

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
	puts("\t-------------------------------------------------------------------------------");
	puts("\t|                    BEM-VINDO AO SISTEMA UNICO DE SAUDE (SUS)                 |");
	puts("\t-------------------------------------------------------------------------------");
	puts("\tAqui receberemos suas informações e iremos direcionar você aos próximos passos!\n");
	puts("\tVAMOS INICIAR O SEU CADASTRO: *(passe álcool nas mãos antes de usar o computador)*\n");

	fflush(stdin);
	printf("\tNOME: ");
    scanf(" %99[^\n]", cadastroCOVID.nome);
    	//fgets(cadastroCOVID.nome, 50, stdin);
	printf("\tIDADE: ");
		scanf("%d",&cadastroCOVID.idade);
	printf("\tCPF (sem ponto/traço): ");
		scanf("%d", &cadastroCOVID.cpf);
	printf("\tCEP (sem ponto/traço): ");
		scanf("%d", &cadastroCOVID.cep);	
}

void sintoma(){// cada atendimento receberá a função sintoma 
	while(1){
        printf("\n\tDigite a QUANTIDADE de sintomas que você está  sentido: (Maximo 10)");
    	scanf("%d", &nSintomas);
        if(nSintomas > 10 || nSintomas < 0){
            printf("\n\t Numero invaldo, digite novamente");
        }else{
            break;
        }

    }
    
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
        	fflush(stdin);//pq não estava pedindo para digitar o primeiro nome
        	printf ("\tDigite o nome: \n");
            scanf(" %99[^\n]", nome_familia[a]);
        	//fgets (nome_familia[a],100,stdin);
    }
}
	
	void isolamento(){//atestado com as informações do paciente e das pessoas que moram com ele
		puts("\n\t---------------------------------------------------------------------");
    	puts("\t|                            ATESTATO MÉDICO                          |");
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
	puts("***ESTA MENSAGEM É DESTINADA À ATENDENTE DO PRONTO SOCORRO.***");
	puts("\n\tDIGITE A DATA DE HOJE");	
    	printf("\tDIA:");
		scanf("%d", &dataATEND.dia);
        printf("\tMÊS:");
		scanf("%d", &dataATEND.mes);
        printf("\tANO:");
		scanf("%d", &dataATEND.ano);
		
	puts("\n\tDigite a quantidade de atendimentos que poderão ser realizados hoje: ");
	scanf("%d", &atend_dia);
	fflush(stdin);//limpando o buffer para receber o nome do médico 
	printf("\n\tDigite o nome do médico responsável pelo pronto socorro no dia de hoje: ");
	scanf(" %99[^\n]", cadastroCOVID.nome_medico);
	system("cls"); //limpando a tela para os pacientes não verem as questões
	
	for(j=0;j<atend_dia;j++){
	
        menu();
    	system("cls");	//limpando a tela para o terminal não ficar cheio 
    	sintoma();
    	system("cls");  //limpando a tela para o terminal não ficar cheio 
	    	
		
	sintoma_8=0;
        for (i = 0; i < nSintomas; i++)
        {
            if (vetSin[i] == 8){
                sintoma_8 = 1;
            }
        }	
			
		//seção de comparações para dizer ao paciente oque deve ser feito
		if((nSintomas>2)&&(sintoma_8 != 1)){//a pessoa tem 3 ou mais sintomas, mas não tem febre, então apenas será realizado o teste
			puts("\n\t---------------------------------------------------------------------");
    		puts("\t|                    SERÁ NECESSÁRIO REALIZAR O TESTE               |");
    		puts("\t---------------------------------------------------------------------");	
			puts("\tAguarde na sala de espera e chamaremos você para fazer o teste do antigeno.");
			puts("\tO resultado do teste sairá em até 2 horas");
		
    	familia();
    	system("cls");//limpar para aparecer só o atestado
	    isolamento();
	    	
		printf("\n\tSE VOCÊ JÁ LEU E ENTENDEU SUA TRIAGEM, TECLE QUALQUER NÚMERO E DEPOIS ENTER:  ");
	     scanf("%d", &leitura_dados);
	   // a_d++;//contador para indicar que já foi um atendimento daqueles que indicamos no início 
	    

		
		}//fim do primeiro if
	    
	    
	    else if((nSintomas<=2)&&(sintoma_8 !=1)){// A pessoa tem 2 ou menos sintomas e não tem febre
		    puts("\n\t---------------------------------------------------------------------");
    		puts("\t|                    NÃO É NECESSÁRIO REALIZAR O TESTE               |");
    		puts("\t---------------------------------------------------------------------");	
		    puts("\n\tPara realizar um teste, é necessário que você possua pelo ");
			puts("\n\tmenos 3 sintomas ou febre. As recomendações são: FIQUE EM CASA");
	   	    puts("\n\t E DESCANSE. ");
	   	    puts("\n\tSe os sintomas se agravarem ou outros aparecerem, ");
			puts("\n\tprocure o atendimento novamente."); 
			puts("\n\n\n\n\n\n");
      		printf("\n                                       Médico responsável: %s\n\n\n", cadastroCOVID.nome_medico);
       		puts("\n\t---------------------------------------------------------------------");	
       			
		//a_d++;//contador para indicar que já foi um atendimento daqueles que indicamos no início 
			
		printf("\n\tSE VOCÊ JÁ LEU E ENTENDEU SUA TRIAGEM, TECLE QUALQUER NÚMERO E DEPOIS ENTER:  ");
	     scanf("%d", &leitura_dados);
		
		system("cls");	//limpando a tela para o proximo pacinete ser atendido	
	   } //fim do segundo if
      
        
		   else if(sintoma_8 == 1){ //independente do numero de sintomas , mas tem febre junto, realizamos o teste e damos medicação pra febre
	   	    puts("\n\t---------------------------------------------------------------------");
    		puts("\t|                        MEDICAÇÃO PARA A FEBRE                      |");
    		puts("\t---------------------------------------------------------------------");
    		puts("\n\tComo você possui sintomas sendo um deles febre, ou só a");
    		puts("\tfebre, você irá receber um medicamento para que ela abaixe. \n ");
    		// depois de indicar que vai medicar, indica que vai fazer o teste.
    		puts("\n\t---------------------------------------------------------------------");
    		puts("\t|                    SERÁ NECESSÁRIO REALIZAR O TESTE               |");
    		puts("\t---------------------------------------------------------------------");	
			puts("\tAguarde na sala de espera e chamaremos você para fazer o teste do Cotonete.");
			puts("\tO resultado do teste sairá em até 10 dias, pois será encaminhado para ");
			puts("\tanálise no LACEN em Florianópolis.");
				familia();
				system("cls");
	            isolamento();
				
			
		printf("\n\tSE VOCÊ JÁ LEU E ENTENDEU SUA TRIAGEM, TECLE QUALQUER NÚMERO E DEPOIS ENTER:  ");
	     scanf("%d", &leitura_dados);
       	}
		else {
			puts("sintoma inválido");
		}
    
		system("cls");	//limpando a tela para o proximo pacinete ser atendido	
	}/// fim do terceiro if
	
	for(j=0;j<nSintomas; j++){
		
		vetSin[j]=0;
		//limpando o vetor de sintomas para o próximo paciente
	}

	
	

		
	return 0; 
}
