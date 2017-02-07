using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Reflection.Emit;
using System.Text;
using System.Threading.Tasks;

namespace howManyLine
{
    public class CountLine
    {
        public int GetAllNumberLineInFile(string nameFile, DirectoryInfo di)
        {
            int allLine = 0;
            foreach (var fi in di.GetFiles(nameFile, SearchOption.AllDirectories))
            {
                Console.WriteLine(fi.Name);
                if (fi.DirectoryName != null)
                {
                    string[] lines = File.ReadAllLines(fi.FullName);
                    Console.WriteLine(lines.Length);
                    allLine = allLine + lines.Length;
                }
            }
            return allLine;
        }
    }
}
