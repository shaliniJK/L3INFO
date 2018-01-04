#include <unistd.h>
#include <stdio.h>
#include <limits.h>

/*Afficher la valeur des constantes NAME_MAX et PATH_MAX*/
int main ()    
{
    printf("Name max = %i\n", NAME_MAX);
    printf("Path max = %i\n", PATH_MAX);
    return 0;
}
