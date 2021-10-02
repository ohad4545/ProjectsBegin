/*Ohad Cohen 208113332 Mickey Vaisman 313552473*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define FEATURES 8 /*each room is featured with a string with the length of 8*/
#define EMPTY 'E'
#define OCUPPIED 'O'
#define COUPLE 'D'
#define FAMILY 'F'
#define SWITA 'S'
#define INDEPENDENT 'I'
#define BEACH 'B'
#define POOL 'P'
#define GARDEN 'G'
#define NONE 'N'
#define SERVICE 200
#define SECURITY 100

/*Part one: Opening the hotel*/
int getting_number_of_rows();
int getting_number_of_columns();
void initialize_hotel(char ****hotel,int n,int m);
void room_features(char ***hotel,int n,int m);

/*Part two: menu*/
int main_menu();
/*Option 1(rent room) functions*/
char i_or_g();
void rent_room(char ****hotel,int n,int m,int number_of_rooms_needed);
int getting_number_of_days();
int getting_id_guess();
int getting_num_people_in_room();
char kind_of_room_transletor(int num_of_people_in_room);
char getting_kind_of_view();

/*Option 2(evacuate a guess) function*/
int evacuation_room(char ****hotel,int n,int m);

/*Option 3(close the hotel) function*/
void free_memory_allocations(char ****hotel,int n,int m);

int main()
{
    /*This program meant for managing a hotel according to specific criterias that will be explained latter in the program function's*/
    /*In this program,at the input checking functions, there will be use in this order: while(fgetc(stdin)!='\n');*/
    /*This order prevent an infinite loop at the case which i want integer from the user and he/she writes char*/
    int n,m;
    char **hotel;
    printf("Please enter the size of the hotel represented by rows and columns:\n");
    n = getting_number_of_rows();
    m = getting_number_of_columns();
    room_features(&hotel,n,m);

    return 0;
}

int getting_number_of_rows()
{
    /*This function meant for getting number of rows from the user and checks the input the user inserted according to the hw criterias*/
    /*the function return the valid input the user inserted*/

    int n,flag_n=0;

    /*Getting number of rows and input checking*/
    do
    {
        printf("rows: ");
        if(scanf("%d", &n) < 1 || n<=2)
        {
            printf("rows number should be bigger than 2.\n");
            while(fgetc(stdin)!='\n');
            flag_n=1;
        }
        else
        {
            flag_n=0;
        }
    }while(flag_n>0);

    return n;
}

int getting_number_of_columns()
{
    /*This function meant for getting number of rows from the user and checks the input the user inserted according to the hw criterias*/
    /*the function return the valid input the user inserted*/

    int m,flag_m=0;

    /*Getting number of rows and input checking*/
    do
    {
        printf("columns: ");
        if(scanf("%d", &m) < 1 || m<0 || m%2 == 0)
        {
            printf("columns number should be odd positive number.\n");
            while(fgetc(stdin)!='\n');
            flag_m=1;
        }
        else
        {
            flag_m=0;
        }
    }while(flag_m>0);

    return m;
}

void initialize_hotel(char ****hotel,int n,int m)
{
    /*This function allocates memory for our hotel.*/
    /* *(hotel) parameter will get a memory allocation (depend on success of memory allocation) of array of n pointers to pointers(n = number of rows of the hotel)*/
    /* *((*hotel)+i) represents each row of the hotel. each row will get a memory allocation (depend on success of memory allocation) of m(number of columns of the hotel) pointers to(char*) */
    /* *((*((*(hotel))+i)) + j) parameter represents each room or corridor in the hotel and each one will get a memory allocation (depend on success of memory allocation) of a string in length of FEATURES(8)*/

    int i,j;
    (*hotel) = (char***)malloc(n * (sizeof(char**)));
    /*memory allocation of (*hotel) parameter and allocation checking */
    if((*hotel) == NULL)
    {
        printf("Allocation failed\n");
        exit(1);
    }


    /*memory allocation of *((*hotel) + i) parameter and allocation checking */
    for(i=0;i<n;i++)
    {
        *((*hotel) + i)=(char**)malloc(m * sizeof(char*));
        if(*((*hotel) + i) == NULL)
        {
            printf("Allocation failed\n");
            exit(1);
        }
    }

    /*memory allocation of *((*((*(hotel))+i)) + j) parameter and allocation checking */
    for(i=0;i<n;i++)
    {
        for(j=0;j<m;j++)
        {
            *((*((*(hotel))+i)) + j)=(char*)calloc(FEATURES,sizeof(char)); /*i want to initialize the whole room's strings to 0 first*/
            if((*((*hotel)+i) + j) == NULL)
            {
                printf("Allocation failed\n");
                exit(1);
            }
        }
    }
}

void room_features(char ***hotel,int n,int m)
{
    /*This function meant to get the initialize room features according to the user features' inputs and update the hotel rooms features according to the option chose in part two menu*/

    int i,j,option,number_of_rooms_needed,flag_num_rood_needed=0,total_hotel_profit=0;
    char kind_of_guest;

    initialize_hotel(&hotel,n,m);

    getchar();/*i did it so the enter that the user clicked after he inserts the number of columns wont count at the first gets(*((*(hotel+i)) + j)) */

    /*Getting the hotel features from the user*/
    for(i=0;i<n;i++)
    {
        for(j=0;j<m;j++)
        {
            if(j == 0 || j == m-1) /*getting all the corner rooms features*/
            {
                printf("insert feature:\n");
                gets(*((*(hotel+i)) + j));
            }

            else if(j%2 == 0 && j != 0 && j != m-1 && i != 0 && i != n-1) /*getting the whole other rooms features*/
            {
                printf("insert feature:\n");
                gets(*((*(hotel+i)) + j));
            }
        }
    }

    /*Part two: main menu and updating hotel features*/

    option = main_menu();
    while(option != 3)
    {
        if(option == 1) /*rent room or rooms*/
        {
           kind_of_guest = i_or_g();
           if(kind_of_guest == 'I')
           {
                number_of_rooms_needed=1;
                rent_room(&hotel,n,m,number_of_rooms_needed);
           }
           if(kind_of_guest == 'G')
           {
               printf("How many rooms do you need in total? ");

               /*Getting the number of rooms needed and input checking*/
               do{
                   if(scanf("%d", &number_of_rooms_needed) < 1 || number_of_rooms_needed <= 0)
                   {
                       printf("Number of rooms should be 1 or more:");
                       while(fgetc(stdin)!='\n');
                       flag_num_rood_needed = 1;
                   }
                   else
                   {
                       flag_num_rood_needed = 0;
                   }
               }while(flag_num_rood_needed > 0);

               rent_room(&hotel,n,m,number_of_rooms_needed);
           }
        }

        if(option == 2)
        {
            total_hotel_profit += evacuation_room(&hotel,n,m);
        }

        option = main_menu();
    }

    /*the guess chose option 3: close the hotel*/
    printf("The total profit of the hotel is: %d $ \n", total_hotel_profit);

    /*free all memory allocations*/
    free_memory_allocations(&hotel,n,m);
}

int main_menu()
{
    /*This function meant for printing the menu of options to the user and checking the user's input*/
    /*The function returns the valid option the user chose*/

    int option,flag_of_option=0;

    /*Getting the option and input checking*/
    do{
        printf("1. Rent room\\ rooms.\n");
        printf("2. check out.\n");
        printf("3. close hotel.\n");
        printf("choose option:");

        if(scanf("%d", &option) < 1 || (option != 1 && option != 2 && option != 3))
        {
            printf("choose option between 1-3:\n");
            while(fgetc(stdin)!='\n');
            flag_of_option=1;
        }
        else
        {
            flag_of_option=0;
        }
    }while(flag_of_option > 0);

    return option;
}

char i_or_g()
{
    /*This function gets if the guest is I(independent) or G(part of group) and do an input checking*/
    /*The function returns the valid kind of guest input*/
    int flag_kind_of_guest=0;
    char kind_of_guest;
    getchar();
    printf("Do you want to order individually or in groups? ");
    do{
        if(!(kind_of_guest = getchar()) || (kind_of_guest != 'I' && kind_of_guest != 'G'))
        {
            printf("Choose between I as personal and G as group? ");
            getchar();
            flag_kind_of_guest=1;
        }
        else
        {
            flag_kind_of_guest=0;
        }
    }while(flag_kind_of_guest > 0);

    return kind_of_guest;
}

void rent_room(char ****hotel,int n,int m,int number_of_rooms_needed)
{
    /*This function meant for update the details of the guess who rents a room in our hotel array*/
     char guess_details[FEATURES],id_string[4];
     char kind_of_room,kind_of_view,tmp_kind_of_room;/*tmp_kind_of_room parameter was meant for getting back to the kind of room the guess wanted if the view goes down the level*/
     int number_of_days,id_guess,num_people_in_room,i,j,flag_of_room_found=0,count_rented_rooms=0,count_empty_rooms=0,k;

     /*counting the number of empty rooms if it is a group*/
     for(i=0;i<n;i++)
     {
         for(j=0;j<m;j++)
         {
             if(*((*((*((*(hotel))+i)) + j)) + 0) == EMPTY)
             {
                count_empty_rooms++;
             }
         }
     }
     if(number_of_rooms_needed > count_empty_rooms)
     {
        printf("Sorry we do not have enough rooms left.\n");
        return;
     }

     printf("Some details should be provided to choose you a satisfying room:\n");
     guess_details[0] = EMPTY;

     /*number of days*/
     number_of_days = getting_number_of_days();
     guess_details[3] = '0' + number_of_days; /*the number of days but as char*/

     /*Id*/
     printf("Id : ");
     id_guess = getting_id_guess();
     sprintf(id_string, "%d", id_guess); /*converting id of guess to string*/

     for(i=0;i<4;i++)/*copying the id of the guess to the guess details array*/
     {
        guess_details[i+4] = id_string[i];
     }

     /*number of people in room*/
     while(number_of_rooms_needed != 0)
     {
         flag_of_room_found=0;
         num_people_in_room = getting_num_people_in_room();
         kind_of_room = kind_of_room_transletor(num_people_in_room);
         guess_details[1] = kind_of_room;
         kind_of_view = getting_kind_of_view();
         guess_details[2] = kind_of_view;
         tmp_kind_of_room = kind_of_room;
         /*finding a fit empty room according to number of people*/
         /*goes down on the level of the kind of view according to the hw criterias and if does not find a fit kind of room and kind of view...*/
         /*goes up on the level of the kind of room according to the hw criterias and initialize back to the kind of view the guess chose*/
         /*until a fit room is found or an error massage and return*/
         while(flag_of_room_found != 1) /*if flag_of_room_found !=1 it means that a room was not found for the guess*/
         {
             guess_details[2] = kind_of_view;
             for(i=0;i<n && flag_of_room_found==0;i++)
             {
                 for(j=0;j<m && flag_of_room_found==0;j++)
                 {
                     if(guess_details[0] == *((*((*((*(hotel))+i)) + j))) && guess_details[1] == *((*((*((*(hotel))+i)) + j)) + 1) && guess_details[2] == *((*((*((*(hotel))+i)) + j)) + 2))
                     {
                         flag_of_room_found=1;
                     }
                 }
             }

             if(flag_of_room_found==0)
             {
                if(kind_of_room == INDEPENDENT)
                {
                    kind_of_room = COUPLE;
                    guess_details[1]=kind_of_room;
                }
                else if(kind_of_room == COUPLE)
                {
                    kind_of_room = FAMILY;
                    guess_details[1]=kind_of_room;
                }
                else if(kind_of_room == FAMILY)
                {
                    kind_of_room = SWITA;
                    guess_details[1]=kind_of_room;
                }
                else if(kind_of_room == SWITA)
                {
                    kind_of_room = tmp_kind_of_room;
                    guess_details[1]=kind_of_room;

                    if(kind_of_view == BEACH)
                    {
                        kind_of_view = POOL;
                    }
                    else if(kind_of_view == POOL)
                    {
                        kind_of_view = GARDEN;
                    }
                    else if(kind_of_view == GARDEN)
                    {
                        kind_of_view = NONE;
                    }
                    else if(kind_of_view == NONE)
                    {
                        printf("Sorry we couldn't fined a room try to register again with different details!\n");

                        /*for the case it was group 'G' and some of the rooms were rented and some of them couldn't found because of number and people and preferred view*/
                        for(i=0;i<n;i++)
                        {
                            for(j=0;j<m;j++)
                            {
                                if(guess_details[4] == *((*((*((*(hotel))+i)) + j)) + 4) && guess_details[5] == *((*((*((*(hotel))+i)) + j)) + 5) && guess_details[6] == *((*((*((*(hotel))+i)) + j)) + 6))
                                {
                                    *((*((*((*(hotel))+i)) + j)) + 0) = EMPTY;
                                    *((*((*((*(hotel))+i)) + j)) + 3) = '#';
                                    *((*((*((*(hotel))+i)) + j)) + 4) = '0';
                                    *((*((*((*(hotel))+i)) + j)) + 5) = '0';
                                    *((*((*((*(hotel))+i)) + j)) + 6) = '0';
                                }
                            }
                        }
                        return;
                    }
                }
             }
         }
        guess_details[1]=kind_of_room;
        guess_details[2]=kind_of_view;
         flag_of_room_found = 0;

        /*an empty room is found */

        /*renting the room*/
        for(i=0;i<n && flag_of_room_found == 0;i++)
        {
            for(j=0;j<m && flag_of_room_found == 0;j++)
            {
                if(guess_details[0] == *((*((*((*(hotel))+i)) + j))) && guess_details[1] == *((*((*((*(hotel))+i)) + j)) + 1) && guess_details[2] == *((*((*((*(hotel))+i)) + j)) + 2))
                {
                    guess_details[0] = OCUPPIED;
                    for(k=0;k<FEATURES;k++)
                    {
                        *((*((*((*(hotel))+i)) + j)) + k) = guess_details[k];
                    }
                    guess_details[0] = EMPTY;
                    count_rented_rooms++;
                    printf("%d room has rented!\n", count_rented_rooms);
                    number_of_rooms_needed--;
                    if(number_of_rooms_needed == 0)
                    {
                        printf("Welcome to our Hotel, may you enjoy your time!\n");
                        return;
                    }
                    flag_of_room_found=1;/*a fit room was found*/
                }
            }
        }
     }
}

int getting_number_of_days()
{
    /*This function meant for getting number of days the guess want to be in the hotel and input checking*/
    /*The function returns the valid number of days the guess inserted*/

    int number_of_days,flag_num_of_days=0;
    printf("How many days will you stay in the hotel:");
    do{
        if(scanf("%d", &number_of_days) < 1 || number_of_days <= 0)
        {
            printf("The number of days should be a positive number: ");
            while(fgetc(stdin)!='\n');
            flag_num_of_days=1;
        }
        else
        {
            flag_num_of_days=0;
        }
     }while(flag_num_of_days>0);

     return number_of_days;
}

int getting_id_guess()
{
    /*This function meant for getting id from the guess and input checking*/
    /*The function returns the valid id the guess inserted*/
    int id_guess,flag_of_id=0;

     do{
         if(scanf("%d", &id_guess) < 1 || (id_guess < 100 || id_guess > 999))
         {

            printf("Id should be three positive digits exactly: ");
            while(fgetc(stdin)!='\n');
            flag_of_id=1;
         }
         else
         {
             flag_of_id=0;
         }
     }while(flag_of_id > 0);

     return id_guess;
}

int getting_num_people_in_room()
{
    /*This function meant for getting number of people in the room from the guess and input checking*/
    /*The function returns the valid number of people in the room the guess inserted*/
    int num_people_in_room,flag_of_people=0;
    printf("number of people in room: ");
    do{
        if(scanf("%d", &num_people_in_room) < 1 || num_people_in_room <= 0)
        {
            printf("roommates should be a positive number: ");
            while(fgetc(stdin)!='\n');
            flag_of_people=1;
        }
        else
        {
            flag_of_people=0;
        }
     }while(flag_of_people>0);

     return num_people_in_room;
}

char kind_of_room_transletor(int num_of_people_in_room)
{
    /*This function meant for translating the number of people to the fit kind of room they deserve as it was wrriten in the strings each room represents*/
    /*The function returns the translation to the fit kind of room*/

    if(num_of_people_in_room == 1)
    {
        return INDEPENDENT;
    }
    if(num_of_people_in_room == 2)
    {
        return COUPLE;
    }
    if(num_of_people_in_room >= 3 && num_of_people_in_room <= 6)
    {
        return FAMILY;
    }
    else /*if(num_of_people_in_room >= 7)*/
    {
        return SWITA;
    }
}

char getting_kind_of_view()
{
    /*This function meant for getting the kind of view the guess inserted and checking the input*/
    /*The function returns the valid kind of view the guess chose*/
    char kind_of_view;
    int flag_view=0;
    getchar();
    printf("preferred view (options: B - beach, P - pool, G - garden, N - None):\n");
    do{
        if(!(kind_of_view = getchar()) || (kind_of_view != BEACH && kind_of_view != POOL && kind_of_view != GARDEN && kind_of_view != NONE))
        {
            printf("you should choose one of these options, B - beach, P - pool, G - garden, N - None:\n");
            getchar();
            flag_view=1;
        }
        else
        {
            flag_view=0;
        }
    }while(flag_view > 0);

    return kind_of_view;
}

int evacuation_room(char ****hotel,int n,int m)
{
    /*This function meant for evacuation the room of the guess by initialize his id's rooms' status to 'E', the char in the rooms features,before the id, to # and the id of room feature back to 000*/
    /*The function returns the total pay of the guess(if id registered),else the function returns 0*/
    int id_guess,i,j,cost_one_night_view,cost_one_night_people_num,count_found_room=0,sum_total_pay=0;
    int number_of_days;
    char id_string[4];
    printf("please insert your id: ");
    id_guess = getting_id_guess();
    sprintf(id_string, "%d", id_guess);

    for(i=0;i<n;i++)
    {
        for(j=0;j<m;j++)
        {
            /*if the id of the guess == the id of the room feature*/
            if(*((*((*((*(hotel))+i)) + j)) + 4) == id_string[0] && *((*((*((*(hotel))+i)) + j)) + 5) == id_string[1] && *((*((*((*(hotel))+i)) + j)) + 6) == id_string[2])
            {
                count_found_room++;
                /*initialize the room status feature and the id feature*/

                *((*((*((*(hotel))+i)) + j)) + 0) = EMPTY;
                *((*((*((*(hotel))+i)) + j)) + 4) = '0';
                *((*((*((*(hotel))+i)) + j)) + 5) = '0';
                *((*((*((*(hotel))+i)) + j)) + 6) = '0';

                /*calculating the total pay*/
                /*kind of room calculation*/

                if(*((*((*((*(hotel))+i)) + j)) + 1) == INDEPENDENT)
                {
                    cost_one_night_people_num = 200;
                }
                if(*((*((*((*(hotel))+i)) + j)) + 1) == COUPLE)
                {
                    cost_one_night_people_num = 350;
                }
                if(*((*((*((*(hotel))+i)) + j)) + 1) == FAMILY)
                {
                    cost_one_night_people_num = 500;
                }
                if(*((*((*((*(hotel))+i)) + j)) + 1) == SWITA)
                {
                    cost_one_night_people_num = 1200;
                }

                /*calculating kind of view*/
                if(*((*((*((*(hotel))+i)) + j)) + 2) == BEACH)
                {
                    cost_one_night_view = 200;
                }
                if(*((*((*((*(hotel))+i)) + j)) + 2) == POOL)
                {
                    cost_one_night_view = 150;
                }
                if(*((*((*((*(hotel))+i)) + j)) + 2) == GARDEN)
                {
                    cost_one_night_view = 100;
                }
                if(*((*((*((*(hotel))+i)) + j)) + 2) == NONE)
                {
                    cost_one_night_view = 0;
                }

                /*the days the guess was at the hotel as int*/
                number_of_days = (*((*((*((*(hotel))+i)) + j)) + 3)) - '0';

                /*summing total pay*/
                sum_total_pay += ((cost_one_night_view + cost_one_night_people_num) * number_of_days + SERVICE + SECURITY);
            }
        }
    }

    if(count_found_room == 0)
    {
       printf("The guest is not registered at the hotel\n");
       return 0;
    }

    printf("Total payment: %d%c \n", sum_total_pay, 36); /*ascii code of char '$'*/
    printf("Hope you enjoyed!\n");
    return sum_total_pay;
}

void free_memory_allocations(char ****hotel,int n,int m)
{
    int i,j;
    for(i=0;i<n;i++)
    {
        for(j=0;j<m;j++)
        {
            free(*((*((*(hotel))+i)) + j));
        }
    }

    for(i=0;i<n;i++)
    {
        free(*((*hotel) + i));
    }

    free((*hotel));
}



