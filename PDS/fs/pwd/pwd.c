#include <stdio.h>
#include <unistd.h>
#include <limits.h>
#include <assert.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <dirent.h>

void mpwd (void)
{
    char buf[PATH_MAX+1];
    getcwd(buf, PATH_MAX);
    printf("%s\n", buf);
}

int is_root(const char * path)
{
    struct stat pathstat;
    struct stat rootstat;
    int status;

    status = stat("/", &rootstat);
    assert(status == 0);
    status = stat(path, &pathstat);
    assert(status == 0);
    return (pathstat.st_ino == rootstat.st_ino &&
            pathstat.st_dev == rootstat.st_dev);
}

static void print_name_in_parent(const char * node, const char * parent)
{
    DIR * dirp;
    struct dirent * dparent;
    struct stat pathstat;
    int status;

    status = stat(node, &pathstat);
    assert(status == 0);
    dirp = opendir(parent);
    assert(dirp != NULL);

    while ((dparent = readdir(dirp))) {
        if (dparent->d_ino == pathstat.st_ino) {
            printf("%s", dparent->d_name);
            closedir(dirp);
            return;
        }
    }
    assert(0);
}


int main (int argc, char **argv)
{
    mpwd();
    return 0;
}
