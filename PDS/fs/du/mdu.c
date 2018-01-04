#include <stdio.h>
#include <unistd.h>
#include <assert.h>
#include <stdarg.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <string.h>
#include <dirent.h>

static int opt_follow_links  = 0;
static int opt_apparent_size = 0;


static int analyse_file (const char *pathname, int depth);
static int expand_directory (const char *pathname, int depth);

void usage (const char *prog)
{
    printf("usage : %s [-b] [-L] file", prog);
}

static int du_file (const char *pathname)
{
    size_t size;
    size = analyse_file(pathname, 0);
    printf("%lu\t%s\n", size, pathname);
    return size;
}

static int analyse_file (const char *pathname, int depth)
{
    struct stat pathstat;
    size_t size;
    int status;

    /* Switch between stat() or lstat() if follow links or not */
    status = (opt_follow_links ? stat : lstat)(pathname, &pathstat);
    assert(status == 0);

    /* Determine apparent size or real size */
    size = opt_apparent_size ? pathstat.st_size : pathstat.st_blocks;

    /* if directory */
    if (S_ISDIR(pathstat.st_mode)) {
        size += expand_directory(pathname, depth);
        if (depth > 0) {
            printf("%lu\t%s\n", size, pathname);
        }
    }
    return size;
}

static int expand_directory (const char *pathname, int depth)
{
    struct dirent * sub_dir;
    DIR * dirp;
    char * entry_path = NULL;
    size_t size = 0;
    size_t pathname_length, entry_path_length;

    pathname_length = strlen(pathname);

    dirp = opendir(pathname);
    assert(dirp != NULL);

    while ((sub_dir = readdir(dirp))) {
        if (strcmp(sub_dir->d_name, ".") == 0 || strcmp(sub_dir->d_name, "..") == 0) {
            continue;
        }

        /* dynamically compute the sub directory entry path, including "/" and "\0" */
        entry_path_length = pathname_length + strlen(sub_dir->d_name) + 2;

        entry_path = realloc(entry_path, entry_path_length * sizeof(char));
        assert(entry_path != NULL);

        strcpy(entry_path, pathname);
        if (entry_path[pathname_length - 1] != '/' && pathname_length > 0) {
            strcat(entry_path, "/");
        }
        strcat(entry_path, sub_dir->d_name);

        size += analyse_file(entry_path, depth + 1);
        /* printf("%lu\t%s\n", size, pathname); */
    }
    free(entry_path);
    closedir(dirp);
    return size;
}

int main (int argc, char **argv)
{
    int ch;
    while ((ch = getopt(argc, argv, "bLh")) != -1) {
        switch (ch) {
            case 'b':
                opt_apparent_size = 1;
                break;
            case 'L':
                opt_follow_links = 1;
                break;
            case 'h':
            default:
                usage(argv[0]);
                exit(EXIT_FAILURE);
        }
    }

    if (optind >= argc) {
        du_file(".");
    }
    else {
        while (optind < argc) {
            du_file(argv[optind]);
            optind++;
        }
    }
    return EXIT_SUCCESS;
}
