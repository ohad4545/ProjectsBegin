#This program is tic tac toe with minimax algorithm
import random
import math
import time
def welcome_message():
    print("Welcome to tic tac toe minimax")

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
    print("This is the representation of each square")
    print('')
    print('|' + '0' + '|' + '1' + '|' + '2' + '|')
    print('|' + '3' + '|' + '4' + '|' + '5' + '|')
    print('|' + '6' + '|' + '7' + '|' + '8' + '|')
    print('')
    time.sleep(3)
    print("At your turn, you will be asked for picking a number which represents the square you want to write your letter in")
    print('')
    print("after you choose the number, click \"enter\" and your turn will be commited")

def gamePlay(board):
    #This function included the whole proccess of the gameplay

    #openning
    welcome_message()
    time.sleep(0.8)
    human_letter = human_choose_letter()
    time.sleep(0.8)
    if human_letter == 'x':
        computer_letter = 'o'
    else:
        computer_letter = 'x'
    time.sleep(0.8)
    print_numbers_board()
    time.sleep(0.8)
    turn_num = 0

    #moves
    while is_empty_places(board):
        # human's turn
        print("human's turn:")
        time.sleep(0.8)
        place = human_choose_move(human_letter,board)
        row = int(place / 3)
        col = int(place % 3)
        if make_and_print_move(board,row,col,human_letter,computer_letter,human_letter,turn_num):
            return
        turn_num += 1
        #it is a tie
        if not is_empty_places(board):
            print("It is a tie!")
            return
        # computer's turn
        print("computer's turn:")
        time.sleep(0.8)
        computer_position = minimaxComputerChooseMove(board,computer_letter,human_letter,turn_num,True)['position']
        if make_and_print_move(board,computer_position[0],computer_position[1],computer_letter,computer_letter,human_letter,turn_num):
            return
        turn_num += 1
        #it is a tie
        if not is_empty_places(board):
            print("It is a tie!")
            return

def make_and_print_move(board,row,col,letter,computer_letter,human_letter,turn_num):
    # this function makes the move of the player and prints the updated board
    # also checks if there is a winner and returns True.else,returns False
    board[row][col] = letter
    print_board(board)
    if check_for_winner(board,computer_letter,human_letter,turn_num):
        return True
    return False

def print_board(board):
    #This function prints the board
    for row in range(3):
        print('|'+board[row][0] +'|' +board[row][1] +'|' +board[row][2]+'|')

def human_choose_move(human_letter,board):
    #This function returns the place that the human player picked

    check_valid_place_value = False
    while(not check_valid_place_value):
        #input checks
        place = input("please pick a place to put your letter[0-8]\n")

        # if the input is a number
        try:
            place = int(place)
        except ValueError:
            print("Invalid value\n")
            continue

        # if the number can represent a place in the board
        if place < 0 or place > 8:
            print("Invalid value\n")
            continue

        # if the place is not already taken
        if board[int(place / 3)][int(place % 3)] != ' ':
            print("this place already taken. try another place\n")
            continue
        check_valid_place_value = True

    return place

def minimaxComputerChooseMove(board,computer_letter,human_letter,turn_num,isMaxPlayer):
    # this function checks all the options and finds the best move the computer player can make

    # calculate the score
    winner_and_score = calculate_score_and_winner(board,computer_letter,human_letter,turn_num)

    #if someone has won, if max_player. the score will be possitive. else, the score will be negative
    if winner_and_score['winner'] == computer_letter or winner_and_score['winner'] == human_letter:
        return {'position':[-1,-1],'score':winner_and_score['score']}

    # tie
    if not is_empty_places(board):
        return {'position':[-1,-1],'score':0}

    if isMaxPlayer:
        best = {'position': [-1,-1], 'score': -math.inf}
    else:
        best = {'position': [-1,-1], 'score': math.inf}

    for possible_move in available_moves(board):
        #1.making the move
        if isMaxPlayer:
            board[possible_move[0]][possible_move[1]] = computer_letter
        else:
            board[possible_move[0]][possible_move[1]] = human_letter

        #2. checking the other options
        if isMaxPlayer:
            tmp_score_and_position = minimaxComputerChooseMove(board, computer_letter, human_letter,turn_num + 1, False)
        else:
            tmp_score_and_position = minimaxComputerChooseMove(board, computer_letter, human_letter, turn_num + 1,True)
        #3. undu the move
        board[possible_move[0]][possible_move[1]] = ' '
        winner_and_score['winner'] ='t'
        # put the possible move in the tmp_score
        tmp_score_and_position['position'] = possible_move

        #4. deciding which one is the best
        if isMaxPlayer:
            if best['score'] < tmp_score_and_position['score']:
                best = tmp_score_and_position

        else:
            if best['score'] > tmp_score_and_position['score']:
                best = tmp_score_and_position

    return best

def is_empty_places(board):
    #this fuction returns True if there are empty places in the board.else, it returns False
    for i in range(3):
        for j in range(3):
            if board[i][j] == ' ':
                return True
    return False

def available_moves(board):
    #This function returns a list of the available moves that remains
    moves =[]
    for i in range(3):
        for j in range(3):
            if(board[i][j] == ' '):
                moves.append([i,j])
    return moves

def calculate_score_and_winner(board,computer_letter,human_letter,turn_num):
    # this function calculates the current score and the current winner of the board (max_player = computer, min_player = human)

    # win in row
    for row in range(3):
        if board[row][0] == board[row][1] and board[row][1] == board[row][2]:
            if board[row][0] == computer_letter:
                return {'winner':computer_letter,'score':1 * (10 - turn_num)}
            if board[row][0] == human_letter:
                return {'winner':human_letter,'score':-1 * (10 - turn_num)}

    # win in column
    for col in range(3):
        if board[0][col] == board[1][col] and board[1][col] == board[2][col]:
            if board[0][col] == computer_letter:
                return {'winner':computer_letter,'score':1 * (10 - turn_num)}
            if board[0][col] == human_letter:
                return {'winner': human_letter, 'score': -1 * (10 - turn_num)}

    #win in diagnol
    if (board[0][0] == board[1][1] and board[1][1] == board[2][2]) or (board[0][2] == board[1][1] and board[1][1] == board[2][0]):
        if board[1][1] == computer_letter:
            return {'winner': computer_letter, 'score': 1 * (10 - turn_num)}
        if board[1][1] == human_letter:
            return {'winner': human_letter, 'score': -1 * (10 - turn_num)}

    # tie (no one has won) 't' = tie
    return {'winner': 't' , 'score':0}

def check_for_winner(board,computer_letter,human_letter,turn_num):
    # this function checks if there is a winner to the game, prints him and returns True.else, it returns False
    if calculate_score_and_winner(board, computer_letter, human_letter, turn_num)['winner'] == computer_letter:
        print("computer wins!")
        return True
    if calculate_score_and_winner(board, computer_letter, human_letter, turn_num)['winner'] == human_letter:
        print("human wins!")
        return True
    return False

if __name__ =='__main__':
    board =[[' ',' ',' '],
            [' ',' ',' '],
            [' ',' ',' ']]
    gamePlay(board)
