import random
import time


# we will divide the classes to game, numbered squares, empty squares, bombs
class mineSweeper():
    def __init__(self,solution_board,player_board):
        self.solution_board = solution_board
        self.player_board = player_board
        self.dug = set()


    @staticmethod
    def welcome_message():
        print("Welcome to minesweeper! ")

    @staticmethod
    def print_initial_board():
        print("  " + "0 " + "1 " + "2 " + "3 " + "4 " + "5 " + "6 " + "7 " + "8 " + "9 ")
        print("0" + "|_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|")
        print("1" + "|_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|")
        print("2" + "|_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|")
        print("3" + "|_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|")
        print("4" + "|_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|")
        print("5" + "|_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|")
        print("6" + "|_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|")
        print("7" + "|_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|")
        print("8" + "|_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|")
        print("9" + "|_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|" + "_|")

    def print_board(self,board):
        for i in range(10):
            print(board[i])

    def building_the_solution_board(self):

        # step1: we put the bombs
        game.put_bombs_on_board()

        # step 2: we put the numbers on the board according to the bombs places
        for row in range(10):
            for col in range (10):
                if self.solution_board[row][col] == '*':
                    continue
                game.put_numbers_on_the_board(row,col)
        print("The solution board is ready")

    def get_move(self):
        row = input("which row would you like to choose[0-9]: ")
        row = int(row)
        col = input("which column would you like to choose[0-9]: ")
        col = int(col)
        while player_board[row][col] != ' ':
            print("You already have made this move. please choose another place")
            row = input("which row would you like to choose[0-9]: ")
            row = int(row)
            col = input("which column would you like to choose[0-9]: ")
            col = int(col)

        if self.solution_board[row][col] == '*':
            return {'position':[row,col],'kind': "bomb"}
        if self.solution_board[row][col] == ' ':
            return {'position':[row,col],'kind': "empty"}
        #the place is a number
        return {'position':[row,col],'kind': "number"}

    def make_move(self,row,col):
        self.dug.add((row, col))
        if self.solution_board[row][col] == '*' or (self.solution_board[row][col] > '0' and self.solution_board[row][col] < '9'):
            self.player_board[row][col] = self.solution_board[row][col]
            return

        # solution_board[row][col] = ' '
        self.player_board[row][col] = '0'
        for i in range(max(0,row-1), min(9,row + 1) + 1):
            for j in range(max(0,col-1), min(9, col + 1) + 1):
                    if (i,j) in self.dug:
                        continue
                     #is dug for the first time
                    self.player_board[i][j] = '0'
                    #return self.make_move_not_bomb(row +1 ,col,player_board,solution_board) and self.make_move_not_bomb(row -1 ,col,player_board,solution_board) and self.make_move_not_bomb(row ,col - 1,player_board,solution_board) and self.make_move_not_bomb(row ,col + 1,player_board,solution_board)
                    self.make_move(i ,j)
        return

    def put_bombs_on_board(self):
        #this function put 10 bombs('*') in 10 random places on the board
        bombs_on_board = 0
        while bombs_on_board < 10:
            place = random.randint(0,99)
            row = int(place / 10)
            col = int(place % 10)
            if self.solution_board[row][col] == ' ':
                self.solution_board[row][col] = '*'
                bombs_on_board += 1

    def put_numbers_on_the_board(self,row,col):
        # this function put the numbers on the board, according the places of the bombs
        bombs_number = 0
        for i in range(max(0, row - 1), min(9, row + 1) + 1):
            for j in range(max(0, col - 1), min(9, col + 1) + 1):
                if i == row and j == col:
                    continue
                if self.solution_board[i][j] == '*':
                    bombs_number += 1
        if bombs_number > 0:
            self.solution_board[row][col] = str(bombs_number)

def gamePlay(game):
    game.welcome_message()
    time.sleep(0.8)
    game.print_initial_board()
    time.sleep(0.8)
    game.building_the_solution_board()
    time.sleep(0.8)
    while len(game.dug) < 10 * 10 - 10: # 10 * 10 -10 = number of squares that are not bombs
        move = game.get_move()
        row = move['position'][0]
        col = move['position'][1]
        game.make_move(row, col)
        game.print_board(game.player_board)
        print(' ')
        time.sleep(0.8)
        if move['kind'] == "bomb":
            print("you have dig a bomb")
            time.sleep(0.8)
            game.print_board(game.solution_board)
            return

    print("congratulations! you win the game!")
    return

if __name__ == '__main__':

    solution_board = [[' ' for _ in range(10)] for _ in range(10)]
    player_board = [[' ' for _ in range(10)] for _ in range(10)]
    game = mineSweeper(solution_board,player_board)
    gamePlay(game)

