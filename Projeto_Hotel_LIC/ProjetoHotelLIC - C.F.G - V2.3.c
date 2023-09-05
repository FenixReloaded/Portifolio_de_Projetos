/*

Caio Pereira
Gabriel Carlos Silva
Felipe Itri
*/

#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
#include <string.h>

//Estrutura de armazenamento dos dados dos h�spedes
typedef struct Hospede
{
    char nome[45];
    char cpf[15]; //Aceita entrada no formato: xxx.xxx.xxx-xx
    char telefone[16]; //Aceita entrada no formato: (xx) xxxx-xxxx
    char endereco[110];
    char email[45];

} tipo_Hospede;

typedef struct Apartamento {
    char status;
    tipo_Hospede hospede;

} tipo_Apartamento;

/**Fun��es
Visualiza��o do mapa do hotel**/

int mapaHotel(tipo_Apartamento hotel[21][15])
{
    int i, j;

    printf("\n\t***Visualiza��o do mapa do hotel***");
    printf("\nApto. -> ");

    //Gerar matriz do quarto
    for (j = 1; j < 15; j++)
    printf(" %2d", j);
    printf("\n\n");

    //Gerar matriz do andar
    for (i = 20; i > 0; i--)
    {
    printf("Andar %2d: ", i);

    for (j = 1; j < 15; j++)
    printf(" %c ", hotel[i][j].status);
    printf("\n");
    }
    printf("\n\n");

    return 0;

}

//Fun��o respons�vel por limpar o buffer do teclado (Para evitar poss�veis erros).

void limparBufferTeclado() {
     char op;

     while( (op = fgetc(stdin)) != EOF && op != '\n') {}
}

//Fun��o destinada a efetuar uma reserva no hotel
int reservarHotel(tipo_Apartamento hotel[21][15])
{
    int i, j;
    do
    {
        printf("Informe o andar desejado:\t");
        scanf("%d", &i);

        printf("Informe o n�mero do quarto a ser efetuada a reserva:\t");
        scanf("%d", &j);

        //Verificar validade dos dados de entrada (para andar e quarto).
        if (i <= 0 || i > 20 || j <= 0 || j > 14)
        {
            printf("O andar e o quarto informados n�o constam em nosso sistema! Por favor, escolha outra op��o!\n");
            continue;
        }
        if (hotel[i][j].status != '.')
        {
            printf("Este quarto j� est� reservado ou ocupado!\n");
            continue;
        }
        else
        {
            //Estrutura destinada a salvar as informa��es do h�spede.
            hotel[i][j].status = 'R';
            limparBufferTeclado();
            printf("Nome: ");
            gets(hotel[i][j].hospede.nome);
            printf("CPF: ");
            gets(hotel[i][j].hospede.cpf);
            printf("Telefone: ");
            gets(hotel[i][j].hospede.telefone);
            printf("Endereco: ");
            gets(hotel[i][j].hospede.endereco);
            printf("Email: ");
            gets(hotel[i][j].hospede.email);

            printf("\n\t\tReserva efetuada com sucesso!!!\n");
        }

    } while (!i || !j);

    return 0;
}

//Fun��o destinada a realizar check_in no hotel.
int check_in(tipo_Apartamento hotel[21][15])
{
    int i, j;

    do
    {
        printf("Informe o andar desejado:\t");
        scanf("%d", &i);
        printf("Informe o n�mero do quarto a ser efetuado o check_in:\t");
        scanf("%d", &j);

        //Estrutura utilizada para verificar validade dos dados de entrada (para andar e quarto).
        if (i <= 0 || i > 20 || j <= 0 || j > 14)
        {
            printf("Entre com um apartamento v�lido! Tente novamente!\n");
            continue;
        }
        if (hotel[i][j].status == 'O')
        {
            printf("Este apartamento j� est� ocupado, escolha outro apartamento!\n");
        }
        else if (hotel[i][j].status == 'R')
        {
            hotel[i][j].status = 'O';
            printf("\n\tO check-in foi realizado com sucesso!!! \n");
            printf("\t\tAproveite sua estadia!!!\n");
        }
        else
        {
            printf("\nN�o consta no sistema nenhuma reserva feita nesse quarto!");
            printf("\n\tPor favor tente novamente.\n");
            break;
        }

    } while (!i || !j);

    return 0;
}

//Fun��o utilizada para realizar check_out no hotel

int check_out(tipo_Apartamento hotel[21][15])
{
    int i, j;

    do
    {
        printf("Informe o andar:\t");
        scanf("%d", &i);

        printf("Informe o n�mero do quarto a ser efetuado o check_out:\t");
        scanf("%d", &j);

        //Verificar validade dos dados de entrada (para andar e quarto).

        if (i <= 0 || i > 20 || j <= 0 || j > 14)
        {
            printf("Entre com um apartamento v�lido! Tente novamente!\n");
            continue;
        }
        else if (hotel[i][j].status == 'R')
            printf("\n\tQuarto n�o ocupado!\n");
        else if (hotel[i][j].status == '.')
            printf("\n\tQuarto n�o ocupado!\n");
        //Essa estrutura � destinada a apagar as informacoes dos hospedes anteriores...
        //...ap�s o check_out da reserva
        else
        {
            hotel[i][j].status = '.';
            hotel[i][j].hospede.nome[0] = '\0';
            hotel[i][j].hospede.cpf[0] = '\0';
            hotel[i][j].hospede.telefone[0] = '\0';
            hotel[i][j].hospede.endereco[0] = '\0';
            hotel[i][j].hospede.email[0] = '\0';
            printf("\n\t\tCheck_out conclu�do com sucesso!!!\n");
            printf("\t\tFoi um prazer t�-lo em nosso hotel.");
            printf("\n\t\t\tVolte sempre!\n\n");
        }

    } while (!i || !j);

    return 0;
}


//Fun��o destinada a cancelar reserva de um quarto no andar solicitado
int cancelarReserva(tipo_Apartamento hotel[21][15])
{
    int i, j;

    do
    {
        printf("Informe o andar:\t");
        scanf("%d", &i);

        printf("Informe o n�mero do quarto para cancelar a reserva:\t");
        scanf("%d", &j);

        //Verificar validade dos dados de entrada (para andar e quarto).
        if (i <= 0 || i > 20 || j <= 0 || j > 14)
        {
            printf("\n\tEntre com um apartamento v�lido! Tente novamente!\n");
            continue;
        }
        else if (hotel[i][j].status == 'O')
            printf("\n\t\tQuarto n�o reservado!\n");
        else if (hotel[i][j].status == '.')
            printf("\n\t\tQuarto n�o reservado!\n");
        //Essa estrutura � destinada a apagar as informacoes dos hospedes anteriores...
        //...ap�s o cancelamento da reserva
        else
        {
            hotel[i][j].status = '.';
            hotel[i][j].hospede.nome[0] = '\0';
            hotel[i][j].hospede.cpf[0] = '\0';
            hotel[i][j].hospede.telefone[0] = '\0';
            hotel[i][j].hospede.endereco[0] = '\0';
            hotel[i][j].hospede.email[0] = '\0';
            printf("\n\t\tReserva cancelada com sucesso!!!\n");
            printf("\t\tFoi um prazer t�-lo em nosso hotel!");
            printf("\n\t\t\tVolte sempre!\n\n");
        }
    } while (!i || !j);
    return 0;
}

//Informa os dados do usuario

void mostrarDados(tipo_Apartamento hotel [21][15])
{
    int i, j;

    printf("\nInforme o andar:\t");
    scanf("%d", &i);
    printf("Informe o n�mero do quarto:\t");
    scanf("%d", &j);

    if (i <= 0 || i > 20 || j <= 0 || j > 14)

    printf("\t\tEntre com um apartamento v�lido! Tente novamente!\n");

    else if(hotel[i][j].status == '.')

    printf("\n\t\tN�o h� reserva nesse quarto!\n");

    else
    {

    printf("\nNome: %s\n", hotel[i][j].hospede.nome);
    printf("CPF: %s\n", hotel[i][j].hospede.cpf);
    printf("Telefone: %s\n", hotel[i][j].hospede.telefone);
    printf("Email: %s\n", hotel[i][j].hospede.email);
    printf("Endereco: %s\n", hotel[i][j].hospede.endereco);
    }
}

//Estrutura destinada a verificar a taxa de ocupa��o do hotel

void taxaOcupacao(tipo_Apartamento hotel[21][15])
{
    float cont_R, cont_O;
    for(int i=1; i<21; i++)
        for(int j=1; j<15; j++)
    {
        switch(hotel [i][j].status)
        {
            case 'O':
                cont_O++;
            break;
            case 'R':
                cont_R++;
            break;

            default:
            break;
        }
    }
    printf("\n\tA quantidade de quartos ocupados � de %5.2f %%!\n", (cont_O*100)/280);
    printf("\tA quantidade de quartos reservados � de %5.2f %%!\n", (cont_R*100)/280);
    printf("\tA taxa de ocupa��o � de %5.2f %%\n", ((cont_O*100)/280)+((cont_R*100)/280));
}

//Bloco principal do c�digo
int main()
{
    //Fun��o respons�vel por permitir caracteres especiais no console
    setlocale(LC_ALL, "Portuguese");
    int opcao, escolha, i, j;
    tipo_Apartamento hotel[21][15];

    int mapaHotel();

    //Mostra o mapa do hotel
    for (i = 20; i > 0; i--)
        for (j = 1; j < 15; j++)
            hotel[i][j].status = '.';

    do //Colocamos esse loop para que a pessoa sempre possa ter uma visao das op��es dispon�veis
    {
        //Menu de op��es
        printf("\n================Bem Vindo ao Hotel EverLake================\n");
        printf("***********************************************************\n");
        printf("\tPor favor, selecione uma das op��es abaixo:\n");
        printf("1. Check-in de h�spede\n");
        printf("2. Check-out de h�spede\n");
        printf("3. Reserva de apartamento\n");
        printf("4. Cancelar uma reserva\n");
        printf("5. Mapa de ocupa��o e reservas do hotel\n");
        printf("6. Informa��es do h�spede\n");
        printf("7. Taxa de ocupa��o e reservas do hotel\n");
        printf("8. Sair  do programa\n");
        printf("***********************************************************\n");

        printf("\nQual op��o voc� deseja?\t");
        scanf("%d", &opcao);

        switch (opcao)
        {
        case 1: //Check-in no hotel
            mapaHotel(hotel);
            check_in(hotel);

            break;

        case 2: //Check-out do hotel
            mapaHotel(hotel);
            check_out(hotel);

            break;

        case 3: //Reservar um quarto no hotel
            mapaHotel(hotel);
            reservarHotel(hotel);

            break;

        case 4: //Cancelamento de uma reserva
            mapaHotel(hotel);
            cancelarReserva(hotel);

            break;

        case 5: //Mapa de ocupacao e reservas do hotel
            mapaHotel(hotel);

            break;

        case 6: //Informacoes do hospede
            mostrarDados(hotel);

            break;

        case 7: //Taxas de ocupacao reservas do hotel
            taxaOcupacao(hotel);

            break;

        case 8://Sa�da do sistema do hotel
            printf("\n\tVoc� realmente deseja sair?\n");
            printf("\t1. Sim \t\t 2. N�o");
            printf("\n\nDigite uma op��o: ");
            scanf("%d", &escolha);

            if (escolha == 1){
                printf("\n\tEstaremos sempre nesse mesmo local! \n\t\tVolte sempre!\n\n");
                printf("\t\t\nSaindo do programa...\n");
                exit(1);
            }
            else
            {
                printf("\n\t\tVoltando para o in�cio...\n");
                main();
            }

        default:
            printf("\tOp��o inv�lida!! Por favor, insira novamente.\n");
            break;
        }
    } while (opcao != 8);

    return 0;
}
