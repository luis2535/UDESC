from socket import *
import os


def send_message(connection, message):
    connection.send(bytes(message, encoding='utf-8'))


def receive_message(connection):
    response = connection.recv(1024)
    return str(response, 'utf-8')


def start_client():
    cont_salas = 0
    server_address = 'localhost'
    server_port = 12000
    client = socket(AF_INET, SOCK_STREAM)
    client.connect((server_address, server_port))
    message = ''
    while (message != 'START'):
        menu()
        message = input('Digite para iniciar: ')
    send_message(client, message)
    print('Você entra numa caverna...')
    while True:
        os.system('clear')
        message = ''
        receive = receive_message(client)
        msg_core = receive.split(';')[0]
        cont_salas = cont_salas + 1;
        if(msg_core == 'MONSTER_ATTACK'):
            monster_attack(receive, cont_salas, client)
        elif (msg_core == 'TAKE_CHEST'):
            chest(receive, cont_salas, client)
        elif (msg_core == 'NOTHING_HAPPENED'):
            nothing(receive, cont_salas)
        elif (msg_core == 'BOSS_EVENT'):
            boss(receive, cont_salas, client)
        elif(msg_core == 'GAME_OVER'):
            vida = int(receive.split(';')[2])
            score = int(receive.split(';')[3])
            print('----------------------------------------------------')
            print('Você tenta continuar, porém perdeu muita vida')
            print('Você não aguenta e cai no chão, sua vida chegou a {}'.format(vida))
            print('Seu herói morreu...')
            print('Você terminou a aventura na sala número {} e com {} de score'.format(cont_salas, score))
            print('GAME OVER')
            print('----------------------------------------------------')
            break
        elif(msg_core == 'WIN'):
            vida = int(receive.split(';')[2])
            score = int(receive.split(';')[3])
            print('----------------------------------------------------')
            if(cont_salas == 21):
                print('Você chegou ao final da caverna com vida.')
            else:
                print('Você alcançou 500 de score.')
            print('Você terminou a aventura com {} pontos de vida e {} de score na sala {}.'.format(vida, score, cont_salas))
            print('Parabéns, você ganhou o jogo.')
            print('----------------------------------------------------')
            break
        while (message != 'WALK'):
            message = input('Digite WALK para ir para próxima sala: ')
            if message != 'WALK':
                print('Mensagem inválida, digite novamente')

        send_message(client, message)


def menu():
    print('----------------------------------------------------')
    print('|            Bem vindo ao jogo RecPG!!!            |')
    print('| Alcance 500 pontos ou passe 20 salas para ganhar |')
    print('|        Digite START para iniciar sua jornada     |')
    print('----------------------------------------------------')


def monster_attack(message, cont_salas, client):
    portas = int(message.split(';')[1])
    vida = message.split(';')[2]
    score = message.split(';')[3]
    retorno = 0
    while (retorno != 1):
        print('----------------------------------------------------')
        print('Sala:{}              Score:{}                Vida:{}'.format(cont_salas, score, vida))
        print('        Você chega a uma sala com {} portas        '.format(portas))
        print('Um monstro está escondido atrás de uma dessas portas')
        print('Escolha uma das portas para tentar atacar o monstro ')
        print('----------------------------------------------------')
        
        action = input('Número da porta: ')
        if (action.isdigit() == False):
            print('Porta inválida, digite novamente.')
        else:
            action = int(action)
            if (action <= portas and action > 0):
                action = str(action)
                send_message(client, action)
                receive = receive_message(client)
                reaction = receive.split(';')[0]
                if (reaction == 'MONSTER_ATTACKED'):
                    print('----------------------------------------------------')
                    print('O monstro estava atrás de outra porta e te atacou')
                    vida = int(vida)
                    vida = vida - 20
                    print('Você perdeu 20 de vida e agora tem {} de vida'.format(vida))
                    print('----------------------------------------------------')
                else:
                    print('----------------------------------------------------')
                    print('Você acertou a porta e matou o monstro')
                    score = int(score)
                    score = score + 40
                    print('Você ganhou 40 de score e agora tem {} de score'.format(score))
                    print('----------------------------------------------------')
                retorno = 1
            else:
                print('Porta inválida, digite novamente.')
            
        

def chest(message, cont_salas, client):
    vida = message.split(';')[1]
    score = message.split(';')[2]
    retorno = 0
    while (retorno != 1):
        print('----------------------------------------------------')
        print('Sala:{}              Score:{}                Vida:{}'.format(cont_salas, score, vida))
        print('        Você chega a uma sala com tesouros        ')
        print('         No centro dessa sala tem um báu            ')
        print('               Deseja abrir esse baú?               ')
        print('----------------------------------------------------')
        action = input('Digite YES para pegar o baú e qualquer outra coisa para passar reto: ')
        send_message(client, action)
        receive = receive_message(client)
        reaction = receive.split(';')[0]
        if (reaction == 'CHEST_VALUE'):
            resultado = int(receive.split(';')[1])
            score = int(score)
            if(resultado < 0):
                print('----------------------------------------------------')
                print('O baú estava amaldiçoado!!!')
                score = score + resultado
                print('Você perdeu {} de score e agora tem {}'.format(resultado, score))
                print('----------------------------------------------------')
            else:
                print('----------------------------------------------------')
                print('O baú estava cheio de tesouros!!!')
                score = score + resultado
                print('Você ganhou {} de score e agora tem {}'.format(resultado, score))
                print('----------------------------------------------------')
            retorno = 1
        elif (reaction == 'SKIPPING_CHEST'):
            print('----------------------------------------------------')
            print('Você acha melhor não tocar no tesouro e segue em frente')
            print('----------------------------------------------------')
            retorno = 1


def nothing(message, cont_salas):
    vida = message.split(';')[1]
    score = message.split(';')[2]
    print('----------------------------------------------------')
    print('Sala:{}              Score:{}                Vida:{}'.format(cont_salas, score, vida))
    print('        Você chega a uma sala vazia                 ')
    print('----------------------------------------------------')

def boss(message, cont_salas, client):
    vida = message.split(';')[1]
    score = message.split(';')[2]
    retorno = 0
    while (retorno != 1):
        print('----------------------------------------------------')
        print('Sala:{}              Score:{}                Vida:{}'.format(cont_salas, score, vida))
        print('     Você chega a uma sala com um monstro gigante   ')
        print('         Esse monstro percebe a sua presença        ')
        print('                 O que deseja fazer?                ')
        print('----------------------------------------------------')
        action = input('Digite RUN para fugir ou FIGHT para lutar: ')
        vida = int(vida)
        score = int(score)
        if (action == 'RUN'):
            send_message(client, action)
            receive = receive_message(client)
            reaction = receive.split(';')[1]
            reaction = int(reaction)
            print('----------------------------------------------------')
            print('Você consegue passar pela sala correndo, mas não sem antes tomar um golpe do monstro.')
            dano = vida - reaction
            print('Você perdeu {} de vida e agora tem {}'.format(dano, reaction))
            print('----------------------------------------------------')
            retorno = 1
        elif (action == 'FIGHT'):
            send_message(client, action)
            receive = receive_message(client)
            reaction = receive.split(';')[0]
            if (reaction == 'BOSS_DEFEATED'):
                print('----------------------------------------------------')
                print('Após uma árdua batalha você consegue derrotar o monstro.')
                newvida = int(receive.split(';')[1])
                newscore = int(receive.split(';')[2])
                print('Ao final da batalha você tem {} de score e {} de vida'.format(newscore, newvida))
                print('----------------------------------------------------')
            elif(reaction == 'FAILED_BOSS_FIGHT'):
                print('----------------------------------------------------')
                print('O monstro é muito mais forte que você.')
                print('Antes dele conseguir te derrotar você consegue fugir da sala.')
                newlife = int(receive.split(';')[1])
                dano = vida - newlife
                print('Você perdeu {} de vida e agora tem {}.'.format(dano, newlife))
                print('----------------------------------------------------')
            retorno = 1
        else:
            print('Mensagem inválida, digite novamente!')
 
        
if __name__ == '__main__':
    start_client()
    