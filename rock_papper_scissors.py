import random
def gameplay():
    round_num = 1
    round_num = int(round_num)
    computer_points = 0
    computer_points = int(computer_points)
    user_points = 0
    user_points = int(user_points)
    player_choise =''
    computer_choise = random.randint(1,3)
    # 1 = rock,2 = papper,3 = scissors
    while(computer_points < 3 and user_points < 3):
        print("round " + str(round_num) +":\n")
        player_choise = input("What is your choise (R = rock,P = papper,S = scissors?:)").lower()

        # 1 point for the computer cases
        if(computer_choise == 1 and player_choise =='s') or (computer_choise == 2 and player_choise =='r') or (computer_choise == 3 and player_choise =='p'):
            computer_points += 1
        # 1 point for the user cases
        if (computer_choise == 1 and player_choise == 'p') or (computer_choise == 2 and player_choise == 's') or (computer_choise == 3 and player_choise == 'r'):
            user_points += 1

        print("end of round "+ str(round_num) + ":\n")
        print("computer points: " +str(computer_points)+"\n")
        print("user points: " + str(user_points) + "\n")
        round_num += 1
        computer_choise = random.randint(1, 3)

    if(computer_points == 3):
        print("Yeah! I,the unbeaten one,I win the game\n")
    else:
    # the user wins
        print("Noooooooo! you are the new king of rock,papper,scissors. you win the game\n")

def start_menu():
    answer =input("welcome to the game rock papper scissors,ready to play with the unbeaten player which is me???YES/NO").lower()
    if(answer == "yes"):
        print("OK!let's start!\n")
        print("The rules are very simple, the first one who gets to 3 points,wins the game.\n")
        print("Good luck!\n")
        return True
    if(answer == "no"):
        print("OK, bye bye\n")
        return False

if(start_menu() == True):
    gameplay()

