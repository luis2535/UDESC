#include <iostream>
using namespace std;


bool isSubsetSum(int set[], int n, int sum)
{
	bool subset[n + 1][sum + 1];                                // O(1)
	for (int i = 0; i <= n; i++)                                // O(N)
		subset[i][0] = true;                                    // O(1)
	for (int i = 1; i <= sum; i++)                              // O(N)
		subset[0][i] = false;                                   // O(1)
	for (int i = 1; i <= n; i++) {                              // O(N)
		for (int j = 1; j <= sum; j++) {                        // O(SUM)
			if (j < set[i - 1])                                 // O(1)
				subset[i][j] = subset[i - 1][j];                // O(1)
			if (j >= set[i - 1])                                // O(1)
				subset[i][j] = subset[i - 1][j]                 // O(1)
							|| subset[i - 1][j - set[i - 1]];   // O(1)
		}
	}
    // Essa parte do código serve apenas para impressão da tabela, por isso não sera considerada para o calculo
    // Porém a complexidade dessa função será de O(N²)
	for (int i = 0; i <= n; i++)
	{
	for (int j = 0; j <= sum; j++)
		printf ("%4d", subset[i][j]);
	cout <<"\n";
	}
	return subset[n][sum];                                      // O(1)
}


int main()
{
	int set[] = { 2, 3, 5, 7, 8, 10, 15 };
	int sum = 23;
	int n = sizeof(set) / sizeof(set[0]);
	if (isSubsetSum(set, n, sum) == true)
		cout <<"Found a subset with given sum";
	else
		cout <<"No subset with given sum";
	return 0;
}
