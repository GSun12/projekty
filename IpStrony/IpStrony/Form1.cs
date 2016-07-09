using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace IpStrony
{
    public partial class Main : Form
    {
        public Main()
        {
            InitializeComponent();
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void I_Load(object sender, EventArgs e)
        {

        }

        private void buttonCheck_Click(object sender, EventArgs e)
        {
            try
            {
                if (!string.IsNullOrWhiteSpace(textBoxInputSite.Text))
                {
                    IPHostEntry hostname = Dns.GetHostEntry(textBoxInputSite.Text);
                    IPAddress[] getIp = hostname.AddressList;
                    textBoxOutput.Text = getIp[0].ToString();
                }
                else
                {
                    IPAddress addr = IPAddress.Parse(textBoxOutput.Text);
                    IPHostEntry entry = Dns.GetHostEntry(addr);
                    textBoxInputSite.Text = entry.HostName;
                }

            }
            catch (Exception ex)
            {
                labelError.Text = "Nie można połączyć się z witryną!";
            }
        }

        private void textBox2_TextChanged(object sender, EventArgs e)
        {

        }
    }
}
