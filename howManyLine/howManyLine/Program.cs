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
            DirectoryInfo di = new DirectoryInfo(@"C:\Users\");
            int allLine = 0;
         

            foreach (var fi in di.GetFiles("*.cs", SearchOption.AllDirectories))
            {
                Console.WriteLine(fi.Name);
                if (fi.DirectoryName != null)
                {
                    string[] lines = File.ReadAllLines(fi.FullName);
                    Console.WriteLine(lines.Length);
                    allLine = allLine + lines.Length;
                }
            }
            Console.WriteLine("koniec liczenia");
            Console.WriteLine(allLine);
            Console.ReadKey();
            
        }
    }
}
