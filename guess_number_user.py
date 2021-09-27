import random
def computer_guess(x):
    computer_start_message = f"I'm gonna guess the number you thought about"
    print(computer_start_message)
    low = 1
    high = x
    comp_guess = random.randint(low,high)
    feedback = ''
    while(feedback !='c'):
        feedback = input(f"is {comp_guess} too high (h),too low(l) or correct(c) ?").lower()
        if(feedback == 'h'):
            high = comp_guess - 1
        elif(feedback =='l'):
            low = comp_guess + 1
        comp_guess = random.randint(low, high)
    print("hell yeah. I guessed your number!")

computer_guess(500)
