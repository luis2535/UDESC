unsigned int potencia (unsigned int b, unsigned int e)
{
    unsigned int r; // O(1)
    if (e == 0) // O(1)
        return 1; // O(1)
    r = potencia(b, e/2);
    if (e % 2 == 0) // O(1)
        return r*r; // O(1)
    else
        return r*r*b; // O(1)
}