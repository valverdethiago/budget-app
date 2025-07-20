import api from './axios';

export const getEnvelopes = () => api.get('/envelopes');
export const getIncome = () => api.get('/envelopes/income');
export const addIncome = (amount) => 
    api.post('/envelopes/income', { value: amount })