using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace howManyLine
{
    class Program
    {
        static void Main(string[] args)
        {
            DirectoryInfo di = new DirectoryInfo(@"C:\Users\gsun1\Documents\GitHub\zespol\mvc\Gym\Gym");
            int allLine = 0;
            string[] listNameOfFile = {"*.cs", "*.cshtml"};
            foreach (var s in listNameOfFile)
            {

               allLine=allLine+ new CountLine().GetAllNumberLineInFile(s,di);


            }
            Console.WriteLine("koniec liczenia");
            Console.WriteLine(allLine);
            Console.ReadKey();
            
        }
    }
}
