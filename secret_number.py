import random
welcome_message = f"welcome to the number guessing game!\n"
print(welcome_message)
start = input("Please click the start range of the number: ")
start = int(start)
end = input("\nPlease click the end range of the number:")
end = int(end)
guess = input("\nnow it is time for you to guess my number: ")
guess = int(guess)
number =random.randrange(start,end,1)
while(guess != number):
    guess = input("sorry ! wrong guess, keep trying\n")
    guess = int(guess)

win_message =f"congratulations!! you have win the game :)"
print(win_message)
