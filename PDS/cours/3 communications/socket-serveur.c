/* Extrait (retravaillé) de la page de manuel unix(7) */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/un.h>
#include <unistd.h>
#include "socket-serveur.h"

int connection_socket;

void terminate() {
    close(connection_socket);

    /* Unlink the socket. */

    unlink(SOCKET_NAME);

    exit(EXIT_SUCCESS);
}

void handle_connection(int data_socket) {
    int down_flag = 0;
    int result;
    char buffer[BUFFER_SIZE];
    int ret;

    result = 0;
    for (;;) {

        /* Wait for next data packet. */

        ret = read(data_socket, buffer, BUFFER_SIZE);
        if (ret == -1) {
            perror("read");
            exit(EXIT_FAILURE);
        }

        /* Ensure buffer is 0-terminated. */

        buffer[BUFFER_SIZE - 1] = 0;

        /* Handle commands. */

        if (!strncmp(buffer, "DOWN", BUFFER_SIZE)) {
            down_flag = 1;
            break;
        }

        if (!strncmp(buffer, "END", BUFFER_SIZE)) {
            break;
        }

        /* Add received summand. */

        result += atoi(buffer);
    }

    /* Send result. */

    sleep(10);                  /* Le temps de calculer... */

    sprintf(buffer, "%d", result);
    ret = write(data_socket, buffer, BUFFER_SIZE);

    if (ret == -1) {
        perror("write");
        exit(EXIT_FAILURE);
    }

    /* Close socket. */

    close(data_socket);

    /* Quit on DOWN command. */

    if (down_flag)
        terminate();
}

int main() {
    struct sockaddr_un name;
    int data_socket;
    int ret;

    /*
     * In case the program exited inadvertently on the last run,
     * remove the socket.
     */

    unlink(SOCKET_NAME);

    /* Create local socket. */

    connection_socket = socket(AF_UNIX, SOCK_SEQPACKET, 0);
    if (connection_socket == -1) {
        perror("socket");
        exit(EXIT_FAILURE);
    }

    /*
     * For portability clear the whole structure, since some
     * implementations have additional (nonstandard) fields in
     * the structure.
     */

    memset(&name, 0, sizeof(struct sockaddr_un));

    /* Bind socket to socket name. */

    name.sun_family = AF_UNIX;
    strncpy(name.sun_path, SOCKET_NAME, sizeof(name.sun_path) - 1);

    ret = bind(connection_socket, (const struct sockaddr *) &name, sizeof(struct sockaddr_un));
    if (ret == -1) {
        perror("bind");
        exit(EXIT_FAILURE);
    }

    /*
     * Prepare for accepting connections. The backlog size is set
     * to 20. So while one request is being processed other requests
     * can be waiting.
     */

    ret = listen(connection_socket, 20);
    if (ret == -1) {
        perror("listen");
        exit(EXIT_FAILURE);
    }

    /* This is the main loop for handling connections. */

    for (;;) {

        /* Wait for incoming connection. */

        data_socket = accept(connection_socket, NULL, NULL);
        if (data_socket == -1) {
            perror("accept");
            exit(EXIT_FAILURE);
        }
        handle_connection(data_socket);
    }

}
