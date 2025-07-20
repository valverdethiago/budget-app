import { useEffect, useState } from 'react'
import './App.css'
import { getIncome, addIncome } from './api/envelopeService';

function App() {
  const [income, setIncome] = useState(null);
  const [newIncome, setNewIncome] = useState(null);

  useEffect(() => {
    const fetchIncome = async () => {
      try {
        const res = await getIncome();
        setIncome(res.data.value);
      } catch(error) {
        console.error('Error fetching income: ', error)
        setIncome(0);
      }
    };
    fetchIncome();
  })

  return (
    <>
      <div>
        <h1>Budget APP</h1>
      </div>
      <section>
        <h2>Income</h2>
        {income !== null ? (
          <p><strong>${Number(income).toFixed(2)}</strong></p>
        ) : (
          <p>Loading income...</p>
        )}
        <div>
          <input 
            type="number"
            step="0.01"
            placeholder="Enter amount"
            value={newIncome}
            onChange={(e) => setNewIncome(e.target.value)} 
          />
          <button 
            onClick={ async () => {
              const amount = parseFloat(newIncome);
              if (!isNaN(amount) && amount >0) {
                await addIncome(amount);
                setNewIncome('');
                const res = await getIncome();
                setIncome(res.data);
              }
            }}
          >
            Add Income
          </button>
        </div>
      </section>
      <section>
        <h2>Create envelope</h2>
        <p>Form goes here</p>
      </section>
      <section>
        <h2>Envelopes</h2>
        <p>List of envelopes goes here...</p>
      </section>
    </>
  )
}

export default App
