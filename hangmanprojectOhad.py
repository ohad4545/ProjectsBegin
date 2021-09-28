def game_title():
    """this function prints the openning of the game"""
    print("""   
|     |
|     |
|     |
|-----|  ____   ______  ______  ____ _____  _____   _____
|     | |    | |      ||      ||    |     ||     | |     |
|     | |    | |      ||      ||    |     ||     | |     |
|     | |    | |      ||      ||          ||     | |     |
|     | |____| |      ||______||          ||_____| |     |
             \                |                  \                                                
              \/              |                   \/                                                  
                        |     |			                       
                        |_____|						
							                                  """)
game_title()
file_path = input("enter a file path:")
# the game gets the file path of the secret words from the user (the file of the secret words is named "words.txt")

def is_exist_file_path(file_path):
    """ this function checks if the file path the user entered is valid and the file exists """
    import os.path
    if os.path.isfile(file_path) == False:
        print("This file is not exist, please run the project again and enter a valid file path and ignore the enter index message")
is_exist_file_path(file_path)

index = input("enter the index of the secret word:")
index = int(index)
# the game gets from the user the index of the secret word he or she wants and the word in the [index - 1] index is the secret word because the index counted from one (not 0)
print("Let's start")
num_of_tries = 0
# this param is int type and represents the number of fail tries the player guessed letters so far
MAX_TRIES = 6
# this param is int typeand represents the maximun tries the player can try guessing letters

def hangman_photos(num_of_tries):
    """ this function gets the number of fail tries the player did in the game and prints the right num of try picture according to the game rules
    : param HANGMAN_PHOTOS : a dictionary that orders the number of try (key) to the right picture according to the game rules (value)
    : type HANGMAN_PHOTOS : dict
    """
    photo_0 = """
    x-------x
    """
    photo_1 = """
    x-------x
    |
    |
    |
    |
    |
    """
    photo_2 = """
    x-------x
    |       |
    |       0
    |       
    |
    |
    """
    photo_3 = """
    x-------x
    |       |
    |       0
    |       |
    |
    |
    """
    photo_4 = """
    x-------x
    |       |
    |       0
    |      /|\       
    |
    |
    """
    photo_5 = """
    x-------x
    |       |
    |       0
    |      /|\       
    |      /
    |
    """
    photo_6 = """
    x-------x
    |       |
    |       0
    |      /|\       
    |      / \  
    |
    """

    HANGMAN_PHOTOS ={'0': photo_0, '1': photo_1, '2': photo_2, '3': photo_3, '4': photo_4, '5': photo_5, '6' :photo_6}
    print(HANGMAN_PHOTOS[str(num_of_tries)])
hangman_photos(num_of_tries)

tuple_of_secret_word =()
# this param will contain the number of whole secret words (not included duplications) in the [0] index and the secret word in the [1] index

def choose_word(file_path , index):
    """
     this function gets the file path and the index from the user and puts in tuple the length of the list of secret words without duplications and the word in [index - 1] index which is the secret word
     : param whole_secret_words : list of the whole secret words with duplications
     : param sorted_whole_secret_words : sorted list of whole_secter_words according to alphabetical order
     : type whole_secret_words : list
     : type sorted_whole_secret_words : list
      : return : the length of the whole secret words without duplications and the secret word choosen by index
      :rtype : tuple"""
    whole_secret_words = []
    open_file_path = open(file_path , 'r')
    for row in open_file_path:
        file_list = row.split(' ')
        whole_secret_words += file_list
    open_file_path.close()
    sorted_whole_secret_words = sorted(whole_secret_words)
    i = 0
    while i < (len (sorted_whole_secret_words) -1):
        # this loop removes the same words that are next to each other until only one word of each kind remains. in this way a no duplication list of secret words is created
        if sorted_whole_secret_words[i] == sorted_whole_secret_words[i+1]:
            sorted_whole_secret_words.remove(sorted_whole_secret_words[i+1])
        else:
            i += 1
    if index > (len(whole_secret_words)):
        # this condition makes the index "run in circles" if the users enters an index that is not exist in the list of whole_secret_words
        index = index % (len (whole_secret_words))
        tuple_of_secret_word = (len(sorted_whole_secret_words) , whole_secret_words[index - 1])
    else:
        tuple_of_secret_word = (len(sorted_whole_secret_words), whole_secret_words[index - 1])
    return tuple_of_secret_word

secret_word = (choose_word(file_path , index))[1]
right_letter_guessed = ['_'] * len(secret_word)
old_letter_guessed = []
# this param is srt type and represents the letter that were guessed in the secret word
def looping_the_game():
    """ this function loops the whole process of the game"""

    def whole_word_guessed(right_letter_guessed):
        """" this function checks if the whole word guessed
        : this function was created to stop the running of the project when the whole word is guessed
        : param m : runs on the indexes of the right_letter_guessed list in "while" loop for checking if the whole word is guessed
        : type m : int
        : return : True, if the whole word is guessed. False, if not
        : rtype : bool
        """
        m = 0
        while m < len(right_letter_guessed):
            if right_letter_guessed[m] =='_':
                return False
            m += 1
        return True
    def check_valid_input(letter_guess, old_letter_guessed):
        """ the function returns False if the letter that is guessed includes 2 chars or above
        : the function returns False if the letter that is guessed includes not an english letter
        : the function returns False if the letter was guessed before
        : the function returns True if the letter that is guessed includes only one char which is an english letter and was not guessed before
        : the function adds a letter that was guessed to a list of guessed before letters and then it can follow about what letters were guessed before and what letters were not
        : param letter_guess : it the letter we get
        : type letter_guess : str
        : param old_letter_guessed : the list of the letters that were guessed before
        : type old_letter_guessed : list
        : return: written in lines 72 -75
        : rtype : boolean
        """
        if ((letter_guess_length > 1) and (only_letters == False)):
            return False
        elif only_letters == False:
            return False
        elif (letter_guess_length > 1) and (only_letters):
            return try_update_letter_guessed(letter_guess, old_letter_guessed)
        elif (letter_guess in old_letter_guessed):
            return try_update_letter_guessed(letter_guess, old_letter_guessed)
        elif try_update_letter_guessed(letter_guess, old_letter_guessed):
            old_letter_guessed += [letter_guess]
        return True

    def try_update_letter_guessed(letter_guess, old_letter_guessed):
        """
        : this funcion checks if the letter guess is more then one letter input and also checks if the letter was already guessed
        : if one of the conditions in line 97 exists , function prints "X" and a sorted list of the guessed letters with "->" between each letter
        : if one of the conditions in line 97 does not exist , the guessed letter is added to the list of the guessed letters
        : return: False if one of the conditions in line 97 exists and True if not
        :rtype : bool
        """
        if (letter_guess_length > 1) and (only_letters):
            print("X")
            old_letter_guessed = sorted(old_letter_guessed, key=str.lower)
            print(list("->".join(old_letter_guessed)))
            return False
        elif (letter_guess in old_letter_guessed):
            print("X")
            old_letter_guessed = sorted(old_letter_guessed, key=str.lower)
            print(list("->".join(old_letter_guessed)))
            return False
        else:
            return True

    def show_hidden_word(secret_word, old_letter_guessed):
        """
        : this function checks if the letter guess is letter that exists in the secret word and print the letter that were guessed already in the word
        : param right_letter_guessed : shows the letter guessed so far in the indexes of the secret word and "_" in the places the letters were'nt guessed yet
        : type right_letter_guessed : str
        : param i : runs with "while" loop on the secret word indexes for checking the letter guess
        : type i : int
        : param he_guess_right : will become True only if the letter guess exists in the secret word
        : type he_guess_right : bool
        : if the letter guess is not in the secret word, the function prints ":( keep guessing" and the function hangman_photos in the number of try
        """
        if check_valid_input(letter_guess, old_letter_guessed):
            i = 0
            he_guess_right = False
            while i < len(secret_word):
                if letter_guess == secret_word[i]:
                    right_letter_guessed[i] = letter_guess[0]
                    he_guess_right = True
                i += 1
            if he_guess_right == False:
                global num_of_tries
                num_of_tries += 1
                if num_of_tries <= MAX_TRIES :
                    hangman_photos(num_of_tries)
            print(' '.join(right_letter_guessed))
        else:
            return check_valid_input(letter_guess , old_letter_guessed)

    def check_win(secret_word, old_letter_guessed):
        """ this function checks if the player wins the game or not according to num_of_tirs param and guessing of the whole secret word letters
        : param num_of_letters : the number of the right letters the player guessed
        :type num_of_letters : int
        : param k : runs with "while" loop on the secret word indexes for creating the final value of num_of_tries
        : type k : int
        : the function prints "WIN" if the player guessed the whole word in less than 7 tries. elif the num_of_tries < 7 the function prints " keep guessing". else , (if the number of tries >6) the function prints "LOSE"
        """
        if num_of_tries <= MAX_TRIES:
            k = 0
            num_of_guessed_letters = 0
            while k < len(secret_word):
                if right_letter_guessed[k] != "_":
                    num_of_guessed_letters += 1
                k += 1
            if num_of_guessed_letters == len(secret_word):
                print("WIN")
            else:
                print("keep guessing , you have %i fail tries" %num_of_tries)
        else:
            print("LOSE")
    while num_of_tries <= MAX_TRIES and whole_word_guessed(right_letter_guessed) == False :
        # this for loop loops the letter guessing of the player
        letter_guess = input("what is the letter you guess?")
        letter_guess = letter_guess.lower()
        # so also if the user enters a uppercase letter it will be considered by the game as an lowercase letter and it will prevent problems in the running of the game because of user input problems (he or she didn't pay attention and their caps lock is on for example)
        letter_guess_length = len(letter_guess)
        only_letters = letter_guess.isalpha()
        show_hidden_word(secret_word, old_letter_guessed)
        check_win(secret_word, old_letter_guessed)
    if num_of_tries > MAX_TRIES :
        check_win(secret_word , old_letter_guessed)
# end of looping_the_game() function
def main():
    if __name__ =="__main__":
        print(looping_the_game())
main()
