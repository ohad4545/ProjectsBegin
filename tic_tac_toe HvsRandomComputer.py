import random
import time
def human_choose_letter():
    human_letter = input("please choose your letter[x or o]").lower()
    while human_letter != 'x' and human_letter != 'o':
        try:
            raise ValueError
        except ValueError:
            print("Invalid letter please choose x or o")
            human_letter = input()
    return human_letter

def print_numbers_board():
    print('|' + '0' + '|' + '1' + '|' + '2' + '|')
    print('|' + '3' + '|' + '4' + '|' + '5' + '|')
    print('|' + '6' + '|' + '7' + '|' + '8' + '|')

def gameplay(board):
    print("welcome to tic tac toe\n")
    time.sleep(0.8)
    count_turn = 0
    human_letter = human_choose_letter()
    time.sleep(0.8)
    print_numbers_board()
    time.sleep(0.8)
    print("Ok. let's start:\n")
    time.sleep(0.8)
    print_board(board)
    time.sleep(0.8)
    if(human_letter =='x'):
        computer_letter ='o'
    else:
        computer_letter ='x'
    while (count_turn < 9):
        print("human's turn: \n")
        time.sleep(0.8)
        place = input("please pick a place to put your letter[0-8]")
        try:
            place = int(place)
        except ValueError:
            print("Invalid value.please pick a number between 0-8")
            continue
        if place < 0 or place > 8:
            print("Invalid value.please pick a number between 0-8")
            continue
        if board[int(place/3)][int(place % 3)] != ' ':
            print("this place already taken. try another place")
            continue
        board[int(place/3)][int(place % 3)] = human_letter
        count_turn += 1
        time.sleep(0.8)
        print_board(board)
        if x_is_winner(board):
            print("x wins!")
            return
        if o_is_winner(board):
            print("o wins!")
            return
        if(count_turn == 9):
            break
        print("computer's turn: \n")
        time.sleep(0.8)
        place = random.randint(0,8)
        while  board[int(place/3)][int(place % 3)] == human_letter or board[int(place/3)][int(place % 3)] == computer_letter:
            place = random.randint(0,8)

        board[int(place / 3)][int(place % 3)] = computer_letter
        count_turn += 1
        print_board(board)
        if x_is_winner(board):
            print("x wins!")
            return
        if o_is_winner(board):
            print("o wins!")
            return
    print("it's a tie!")
    return


def x_is_winner(board):
    #win in row
    if board[0][0] == board[0][1] and board[0][1] == board[0][2] and board[0][2] =='x':
        return True
    if board[1][0] == board[1][1] and board[1][1] == board[1][2] and board[1][2] =='x':
        return True
    if board[2][0] == board[2][1] and board[2][1] == board[2][2] and board[2][2] =='x':
        return True
    # win in column
    if board[0][0] == board[1][0] and board[1][0] == board[2][0] and board[2][0] =='x':
        return True
    if board[0][1] == board[1][1] and board[1][1] == board[2][1] and board[2][1] =='x':
        return True
    if board[0][2] == board[1][2] and board[1][2] == board[2][2] and board[2][2] =='x':
        return True
    #win in diagonal
    if board[0][0] == board[1][1] and board[1][1] == board[2][2] and board[2][2] == 'x':
        return True
    if board[0][2] == board[1][1] and board[1][1] == board[2][0] and board[2][0] == 'x':
        return True
    return False

def o_is_winner(board):
    #win in row
    if board[0][0] == board[0][1] and board[0][1] == board[0][2] and board[0][2] =='o':
        return True
    if board[1][0] == board[1][1] and board[1][1] == board[1][2] and board[1][2] =='o':
        return True
    if board[2][0] == board[2][1] and board[2][1] == board[2][2] and board[2][2] =='o':
        return True
    # win in column
    if board[0][0] == board[1][0] and board[1][0] == board[2][0] and board[2][0] =='o':
        return True
    if board[0][1] == board[1][1] and board[1][1] == board[2][1] and board[2][1] =='o':
        return True
    if board[0][2] == board[1][2] and board[1][2] == board[2][2] and board[2][2] =='o':
        return True
    #win in diagonal
    if board[0][0] == board[1][1] and board[1][1] == board[2][2] and board[2][2] == 'o':
        return True
    if board[0][2] == board[1][1] and board[1][1] == board[2][0] and board[2][0] == 'o':
        return True
    return False

def print_board(board):
    for row in range(3):
        print('|'+board[row][0] +'|' +board[row][1] +'|' +board[row][2]+'|')

board =[[' ',' ',' '],
        [' ',' ',' '],
        [' ',' ',' ']]
gameplay(board)