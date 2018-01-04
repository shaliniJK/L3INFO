#include <unistd.h>
#include <stdio.h>
#include <limits.h>
#include <stdlib.h>
#include <assert.h>

char** filldirs (){
    char ** dirs;
    char * path, * tmp;
    int taille;

    path = getenv("PATH");
    assert(path!=NULL);
    taille=2;
    for (tmp=path;*tmp!='\0';tmp++){
        if(*tmp==':') taille++;
    }
    dirs = malloc (taille *sizeof(char *));
    assert (dirs!=NULL);
    dirs[--taille]=NULL;
    dirs[--taille]=path;
    for (tmp=path;*tmp!='\0';tmp++){
        if(*tmp==':') {
            *tmp='\0';
            dirs[--taille]=tmp+1;
        }
    }
    return dirs;
}

int which (char * cmd, char ** dirs) {
    char tmp[PATH_MAX+1];
    int i;
    for (i=0;dirs[i];i++){
        snprintf(tmp,PATH_MAX+1,"%s/%s",dirs[i],cmd);
        if (!access(tmp,F_OK)) {
            puts(tmp);
            return 1;
        }
    }
    fprintf(stderr,"Commande %s non trouve",cmd);
    return 0;
}

int main (int argc, char * argv[]) {
    int res = 1,i;
    char **dirs;
    dirs = filldirs();
    for (i=1;i<argc;i++) res = res && which(argv[i],dirs);
    return !res;
}